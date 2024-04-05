package com.marlen.pixabay.presentation.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.marlen.pixabay.domain.DeviceRepository
import com.marlen.pixabay.domain.InternetConnectivityRepository
import com.marlen.pixabay.domain.PixabayRepository
import com.marlen.pixabay.domain.models.DeviceOrientation
import com.marlen.pixabay.presentation.search.model.ImageUIModel
import com.marlen.pixabay.presentation.search.model.toUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val pixabayRepository: PixabayRepository,
    private val deviceRepository: DeviceRepository,
    private val savedStateHandle: SavedStateHandle,
    private val internetConnectivityRepository: InternetConnectivityRepository
) : ViewModel() {

    private val _navigationEvent = MutableStateFlow<SearchNavigationEvent?>(null)
    val navigationEvent = _navigationEvent.asStateFlow()

    private val _state = MutableStateFlow(initialSearchViewState())
    val state = _state.asStateFlow()

    init {
        setupSearch()
        listerInternet()
    }

    private fun listerInternet() {
        viewModelScope.launch {
            internetConnectivityRepository.isInternetAvailable.collect { isAvailable ->
                _state.update {
                    it.copy(isInternetAvailable = isAvailable)
                }
            }
        }
    }

    private fun search(searchQuery: String) {
        savedStateHandle[HANDLE_STATE_SEARCH_QUERY_KEY] = searchQuery
        _state.update {
            it.copy(
                images = pixabayRepository
                    .searchImages(searchQuery)
                    .map { it.map { it.toUIModel(getImageMaxWidth()) } }
                    .cachedIn(viewModelScope)
            )
        }
    }

    private fun getImageMaxWidth() = deviceRepository.getScreenSize().width / PORTRAIT_IMAGE_CELL_COUNT

    @OptIn(FlowPreview::class)
    private fun setupSearch() {
        viewModelScope.launch {
            _state
                .distinctUntilChangedBy { uiState -> uiState.searchQuery }
                .debounce(SEARCH_DELAY)
                .collectLatest { uiState -> search(uiState.searchQuery) }
        }
    }

    fun searchQueryChange(searchQuery: String) {
        _state.update {
            it.copy(searchQuery = searchQuery)
        }
    }

    fun clearSearchClick() {
        _state.update {
            it.copy(searchQuery = "")
        }
    }

    fun orientationChanged() {
        _state.update {
            it.copy(cellsCount = getImageCellCount())
        }
    }

    fun imageClick(images: ImageUIModel) {
        _state.update {
            it.copy(dialog = SearchViewDialog.OpenImageDetailViewDialog(images.id))
        }
    }

    fun dialogDismiss(searchViewDialog: SearchViewDialog) {
        _state.update {
            it.copy(dialog = null)
        }
    }

    fun dialogConfirm(dialog: SearchViewDialog) {
        dialogDismiss(dialog)
        when (dialog) {
            is SearchViewDialog.OpenImageDetailViewDialog -> navigateToDetail(dialog.imageId)
        }
    }

    private fun navigateToDetail(imageId: Int) {
        _navigationEvent.value = SearchNavigationEvent.NavigateToDetail(imageId)
    }

    fun resetNavigationEvent() {
        _navigationEvent.update {
           null
        }
    }

    private fun getImageCellCount() = when (deviceRepository.getDeviceOrientation()) {
        DeviceOrientation.Portrait -> PORTRAIT_IMAGE_CELL_COUNT
        DeviceOrientation.Landscape -> LANDSCAPE_IMAGE_CELL_COUNT
    }

    private fun initialSearchViewState() = SearchViewState(
        images = flow { },
        searchQuery = savedStateHandle.get<String>(HANDLE_STATE_SEARCH_QUERY_KEY) ?: DEFAULT_SEARCH_QUERY,
        cellsCount = getImageCellCount(),
        dialog = null,
        isInternetAvailable = internetConnectivityRepository.isInternetAvailable.value
    )

    companion object {
        private const val SEARCH_DELAY = 300L
        private const val PORTRAIT_IMAGE_CELL_COUNT = 2
        private const val LANDSCAPE_IMAGE_CELL_COUNT = 3
        private const val DEFAULT_SEARCH_QUERY = "fruits"
        private const val HANDLE_STATE_SEARCH_QUERY_KEY = "HANDLE_STATE_SEARCH_QUERY_KEY"
    }

}

data class SearchViewState(
    val images: Flow<PagingData<ImageUIModel>>,
    val searchQuery: String,
    val cellsCount: Int,
    val dialog: SearchViewDialog?,
    val isInternetAvailable: Boolean
)

sealed interface SearchViewDialog {

    data class OpenImageDetailViewDialog(
        val imageId: Int
    ) : SearchViewDialog
}

sealed class SearchNavigationEvent {
    data class NavigateToDetail(
        val imageId: Int
    ) : SearchNavigationEvent()
}