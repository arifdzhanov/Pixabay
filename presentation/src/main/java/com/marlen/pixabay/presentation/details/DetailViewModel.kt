package com.marlen.pixabay.presentation.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marlen.pixabay.domain.InternetConnectivityRepository
import com.marlen.pixabay.domain.PixabayRepository
import com.marlen.pixabay.domain.models.ImageModel
import com.marlen.pixabay.domain.models.Result
import com.marlen.pixabay.presentation.utils.fromNavArgString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val pixabayRepository: PixabayRepository,
    private val connectivityRepository: InternetConnectivityRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state = _state.asStateFlow()

    private val detailArgs = savedStateHandle.get<String>(DetailsNavArgs.detailArg)
        ?.fromNavArgString<DetailArgs>()

    init {
        loadImage()
        listerInternet()
    }

    fun reload() {
        loadImage()
    }

    private fun listerInternet() {
        viewModelScope.launch {
            connectivityRepository.isInternetAvailable.collect { isAvailable ->
                _state.update {
                    if (it is DetailState.Content)
                        it.copy(isInternetAvailable = isAvailable)
                    else
                        it
                }
            }
        }
    }

    private fun loadGifs(imageId: Int) {
        viewModelScope.launch {
            val result = pixabayRepository.getImageById(imageId = imageId)

            _state.update {
                when (result) {
                    is Result.Error   -> DetailState.Error
                    is Result.Success -> DetailState.Content(
                        image = result.data,
                        isInternetAvailable = connectivityRepository.isInternetAvailable.value
                    )
                }
            }
        }
    }

    private fun loadImage() {
        detailArgs?.let {
            loadGifs(it.imageId)
        }
    }
}

sealed interface DetailState {

    data object Loading : DetailState

    data object Error : DetailState

    data class Content(
        val image: ImageModel,
        val isInternetAvailable: Boolean
    ) : DetailState
}