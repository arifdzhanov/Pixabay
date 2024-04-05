package com.marlen.pixabay.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.marlen.pixabay.data.local.ImagesDatabase
import com.marlen.pixabay.data.remote.PixabayAPI
import com.marlen.pixabay.domain.InternetConnectivityRepository
import com.marlen.pixabay.domain.PixabayRepository
import com.marlen.pixabay.domain.models.DataError
import com.marlen.pixabay.domain.models.ImageModel
import com.marlen.pixabay.domain.models.Result
import javax.inject.Provider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalPagingApi::class)
class PixabayRepositoryImpl(
    private val database: ImagesDatabase,
    private val remoteMediator: Provider<ItemRemoteMediator.Factory>,
    ) : PixabayRepository {

    private val itemDao = database.itemDao()


    override fun searchImages(query: String): Flow<PagingData<ImageModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            remoteMediator = remoteMediator.get().create(query),
            pagingSourceFactory = { itemDao.getItems(query) }
        ).flow
            .map { entities -> entities.map { entity -> entity.mapToDomain() } }
    }

    override suspend fun getImageById(imageId: Int): Result<ImageModel, DataError.Local> {
        return try {
            Result.Success(
                database.itemDao().getImage(imageId).mapToDomain()
            )
        } catch (e: Exception){
            Result.Error(DataError.Local.DATABASE_ERROR)
        }
    }
}

