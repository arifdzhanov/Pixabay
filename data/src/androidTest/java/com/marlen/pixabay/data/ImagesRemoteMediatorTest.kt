package com.marlen.pixabay.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.marlen.pixabay.data.local.ImagesDatabase
import com.marlen.pixabay.data.local.entity.ImageEntity
import com.marlen.pixabay.data.remote.PixabayAPI
import com.marlen.pixabay.data.remote.getSearchEmptyResults
import com.marlen.pixabay.data.remote.getSearchResults
import com.marlen.pixabay.domain.InternetConnectivityRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.runner.RunWith
import java.net.UnknownHostException

@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class ImagesRemoteMediatorTest {

    private val db: ImagesDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(), ImagesDatabase::class.java
    ).build()
    private val api: PixabayAPI = mockk()
    private val connectivityRepository: InternetConnectivityRepository = mockk()
    private val searchQuery = "searchQuery"

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        coEvery { api.searchImages(any(), any(), any()) } returns getSearchResults()
        coEvery { connectivityRepository.isInternetAvailable } returns MutableStateFlow(true)
        val remoteMediator = ItemRemoteMediator(
            database = db, service = api, internetConnectivityRepository = connectivityRepository, searchQuery = searchQuery
        )
        val pagingState = PagingState<Int, ImageEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertEquals(true, true)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        coEvery { api.searchImages(any(), any(), any()) } returns getSearchEmptyResults()
        coEvery { connectivityRepository.isInternetAvailable } returns MutableStateFlow(true)
        val remoteMediator = ItemRemoteMediator(
            database = db, service = api, internetConnectivityRepository = connectivityRepository, searchQuery = searchQuery
        )
        val pagingState = PagingState<Int, ImageEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @Test
    fun refreshLoadErrorWhenApiServiceThrowsError() = runBlocking {
        val unknownHostException = UnknownHostException()
        coEvery { api.searchImages(any(), any(), any()) } throws unknownHostException
        coEvery { connectivityRepository.isInternetAvailable } returns MutableStateFlow(true)
        val remoteMediator = ItemRemoteMediator(
            database = db, service = api, internetConnectivityRepository = connectivityRepository, searchQuery = searchQuery
        )
        val pagingState = PagingState<Int, ImageEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }

    @Test
    fun refreshLoadErrorWhenNoInternet() = runBlocking {
        coEvery { api.searchImages(any(), any(), any()) }  returns getSearchEmptyResults()
        coEvery { connectivityRepository.isInternetAvailable } returns MutableStateFlow(false)
        val remoteMediator = ItemRemoteMediator(
            database = db, service = api, internetConnectivityRepository = connectivityRepository, searchQuery = searchQuery
        )
        val pagingState = PagingState<Int, ImageEntity>(
            listOf(), null, PagingConfig(10), 10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue(result is RemoteMediator.MediatorResult.Error)
    }
}