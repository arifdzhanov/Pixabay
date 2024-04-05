package com.marlen.pixabay.domain

import kotlinx.coroutines.flow.StateFlow

interface InternetConnectivityRepository {

    val isInternetAvailable: StateFlow<Boolean>

    suspend fun setIsInternetAvailable(isInternetAvailable: Boolean)
}