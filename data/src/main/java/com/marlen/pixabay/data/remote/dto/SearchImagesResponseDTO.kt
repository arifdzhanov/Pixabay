package com.marlen.pixabay.data.remote.dto

data class SearchImagesResponseDTO(
    val hits: List<HitDTO>,
    val total: Int,
    val totalHits: Int
)