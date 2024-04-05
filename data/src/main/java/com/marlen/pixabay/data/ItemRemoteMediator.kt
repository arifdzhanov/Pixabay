package com.marlen.pixabay.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.marlen.pixabay.data.local.ImagesDatabase
import com.marlen.pixabay.data.local.entity.ImageEntity
import com.marlen.pixabay.data.remote.PixabayAPI
import com.marlen.pixabay.domain.InternetConnectivityRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class ItemRemoteMediator @AssistedInject constructor(
    private val database: ImagesDatabase,
    private val service: PixabayAPI,
    private val internetConnectivityRepository: InternetConnectivityRepository,
    @Assisted("searchQuery") private val searchQuery: String,
) : RemoteMediator<Int, ImageEntity>() {

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("searchQuery") searchQuery: String): ItemRemoteMediator
    }

    private val itemDao = database.itemDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, ImageEntity>): MediatorResult = withContext(Dispatchers.IO) {
        return@withContext try {

            val pageSize = state.config.pageSize
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    1
                }

                LoadType.PREPEND -> return@withContext MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND  -> {
                    itemDao.count(searchQuery) / pageSize + 1
                }
            }

            if (!internetConnectivityRepository.isInternetAvailable.value) {
                return@withContext MediatorResult.Error(Exception("No internet"))
            }

            val response = service.searchImages(
                key = "43133187-5ede138bd9b5f0944ba76fff2",
                query = searchQuery,
                page
            )

            if (page == 1) {
                itemDao.clearItemsForQuery(searchQuery)
            }
            val items = response.hits.mapIndexed { index, dto ->
                dto.mapToEntity(
                    itemIndex = index,
                    pageIndex = page,
                    query = searchQuery
                )
            }

            itemDao.insertAll(items)
            MediatorResult.Success(endOfPaginationReached = false)
        } catch (exception: Exception) {
            MediatorResult.Error(exception)
        }
    }
}