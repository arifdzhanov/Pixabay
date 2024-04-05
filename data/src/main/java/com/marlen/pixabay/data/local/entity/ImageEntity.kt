package com.marlen.pixabay.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("remote_id")
    val remoteId: Int,
    @ColumnInfo("web_format_url")
    val webFormatUrl: String,
    @ColumnInfo("large_image_url")
    val largeImageURL: String,
    @Embedded("user_")
    val user: UserEntity,
    @ColumnInfo("tags")
    val tags: List<String>,
    @ColumnInfo("comments")
    val comments: Int,
    @ColumnInfo("likes")
    val likes: Int,
    @ColumnInfo("downloads")
    val downloads: Int,
    @ColumnInfo("aspect_ratio")
    val aspectRatio: Float,
    @ColumnInfo("item_index")
    val itemIndex: Int,
    @ColumnInfo("page_index")
    val pageIndex: Int,
    @ColumnInfo("search_query")
    val searchQuery: String,
)

data class UserEntity(
    val name: String,
    val avatar: String
)