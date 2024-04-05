package com.marlen.pixabay.domain

import androidx.paging.PagingData
import com.marlen.pixabay.domain.models.DataError
import com.marlen.pixabay.domain.models.ImageModel
import kotlinx.coroutines.flow.Flow
import com.marlen.pixabay.domain.models.Result

interface PixabayRepository {

    fun searchImages(query: String): Flow<PagingData<ImageModel>>

    suspend fun getImageById(imageId: Int): Result<ImageModel, DataError.Local>
}