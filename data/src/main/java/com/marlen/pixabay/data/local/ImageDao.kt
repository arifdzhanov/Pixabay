package com.marlen.pixabay.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.marlen.pixabay.data.local.entity.ImageEntity

@Dao
interface ImageDao {
    @Query("SELECT * FROM images WHERE :searchQuery = search_query ORDER BY page_index ASC, item_index ASC")
    fun getItems(searchQuery: String): PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM images WHERE :searchQuery = search_query ORDER BY page_index ASC, item_index ASC")
    suspend fun getAll(searchQuery: String): List<ImageEntity>

    @Query("SELECT COUNT(*) FROM images WHERE :searchQuery = search_query")
    fun count(searchQuery: String): Int

    @Insert()
    suspend fun insertAll(items: List<ImageEntity>)

    @Query("DELETE FROM images WHERE :searchQuery = search_query")
    suspend fun clearItemsForQuery(searchQuery: String)

    @Query("SELECT * FROM images WHERE id=:imageId")
    suspend fun getImage(imageId: Int): ImageEntity
}