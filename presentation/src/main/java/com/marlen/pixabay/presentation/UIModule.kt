package com.marlen.pixabay.presentation

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UIModule {



    @Provides
    @Singleton
    fun providePixabayApi(@ApplicationContext context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .diskCache {
                DiskCache.Builder()
                    .directory(context.cacheDir.resolve("image_cache"))
                    .maxSizePercent(0.25) // Use up to 25% of the available disk space for the cache
                    .build()
            }
            .build()
    }
}