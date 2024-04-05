package com.marlen.pixabay

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marlen.pixabay.domain.InternetConnectivityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val internetConnectivityRepository: InternetConnectivityRepository,
) : ViewModel() {

    fun setInternetAvailable(isInternetAvailable: Boolean) {
        viewModelScope.launch() {
            internetConnectivityRepository.setIsInternetAvailable(isInternetAvailable)
        }
    }
}