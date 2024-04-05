package com.marlen.pixabay.data.remote

import com.marlen.pixabay.data.remote.dto.HitDTO
import com.marlen.pixabay.data.remote.dto.SearchImagesResponseDTO

fun createHitDTO(id: Int) = HitDTO(
    id = id,
    comments = 15,
    downloads = 1023,
    fullHDURL = "https://example.com/fullhd.jpg",
    imageHeight = 1920,
    imageSize = 2048,
    imageURL = "https://example.com/image.jpg",
    imageWidth = 1080,
    largeImageURL = "https://example.com/large.jpg",
    likes = 100,
    pageURL = "https://example.com/page",
    previewHeight = 300,
    previewURL = "https://example.com/preview.jpg",
    previewWidth = 150,
    tags = "nature, water",
    type = "photo",
    user = "JohnDoe",
    userImageURL = "https://example.com/user.jpg",
    user_id = 123,
    views = 5000,
    webformatHeight = 600,
    webformatURL = "https://example.com/webformat.jpg",
    webformatWidth = 300
)

fun getSearchResults(): SearchImagesResponseDTO {
    val hits = mutableListOf<HitDTO>()
    for (i in 1..20) {
        hits.add(createHitDTO(i))
    }
    return SearchImagesResponseDTO(
        hits = hits,
        total = 2000,
        totalHits = 500
    )
}

fun getSearchEmptyResults(): SearchImagesResponseDTO {
    return SearchImagesResponseDTO(
        hits = listOf(),
        total = 2000,
        totalHits = 500
    )
}