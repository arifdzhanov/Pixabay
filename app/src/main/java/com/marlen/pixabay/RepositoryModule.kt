package com.marlen.pixabay

import android.app.Application
import com.marlen.pixabay.data.ItemRemoteMediator
import com.marlen.pixabay.data.PixabayRepositoryImpl
import com.marlen.pixabay.data.local.ImagesDatabase
import com.marlen.pixabay.data.remote.PixabayAPI
import com.marlen.pixabay.domain.InternetConnectivityRepository
import com.marlen.pixabay.domain.PixabayRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {



    @Provides
    @Singleton
    fun providePixabayApi(): PixabayAPI {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return PixabayAPI(
            baseUrl = BuildConfig.PIXABAY_API_URL,
            client = client,
            converterFactory = GsonConverterFactory.create()
        )
    }
}