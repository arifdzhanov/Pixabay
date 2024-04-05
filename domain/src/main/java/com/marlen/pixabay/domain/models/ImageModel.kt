package com.marlen.pixabay.domain.models

data class ImageModel(
    val id: Int,
    val remoteId: Int,
    val webFormatURL: String,
    val largeImageURL: String,
    val user: User,
    val tags: List<String>,
    val comments: Int,
    val likes: Int,
    val downloads: Int,
    val aspectRatio: Float,
)