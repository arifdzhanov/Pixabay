package com.marlen.pixabay.data

import android.app.Application
import com.marlen.pixabay.data.local.ImagesDatabase
import com.marlen.pixabay.data.remote.PixabayAPI
import com.marlen.pixabay.domain.InternetConnectivityRepository
import com.marlen.pixabay.domain.PixabayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePixabayRepository(
        remoteMediator: Provider<ItemRemoteMediator.Factory>,
        database: ImagesDatabase,
    ): PixabayRepository {
        return PixabayRepositoryImpl(
            database = database,
            remoteMediator = remoteMediator,
        )
    }

    @Provides
    @Singleton
    fun provideDataBase(
        application: Application,
    ): ImagesDatabase {
        return ImagesDatabase(application)
    }
}