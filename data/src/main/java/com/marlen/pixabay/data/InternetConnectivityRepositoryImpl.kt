package com.marlen.pixabay.data

import com.marlen.pixabay.domain.InternetConnectivityRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class InternetConnectivityRepositoryImpl @Inject constructor(): InternetConnectivityRepository {

    private val _isInternetAvailable = MutableStateFlow(false)
    override val isInternetAvailable: StateFlow<Boolean>
        get() = _isInternetAvailable.asStateFlow()


    override suspend fun setIsInternetAvailable(isInternetAvailable: Boolean) {
        _isInternetAvailable.emit(isInternetAvailable)
    }
}