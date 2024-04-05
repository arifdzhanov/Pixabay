package com.marlen.pixabay

import com.marlen.pixabay.data.DeviceRepositoryImpl
import com.marlen.pixabay.data.InternetConnectivityRepositoryImpl
import com.marlen.pixabay.domain.DeviceRepository
import com.marlen.pixabay.domain.InternetConnectivityRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternetHiltModule {

    @Binds
    @Singleton
    fun bindInternetConnectivityRepository(
        internetConnectivityRepositoryImpl: InternetConnectivityRepositoryImpl
    ): InternetConnectivityRepository

    @Binds
    @Singleton
    fun bindDeviceRepository(
        deviceRepositoryImpl: DeviceRepositoryImpl
    ): DeviceRepository
}