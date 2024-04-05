package com.marlen.pixabay.data

import com.marlen.pixabay.data.local.entity.ImageEntity
import com.marlen.pixabay.data.local.entity.UserEntity
import com.marlen.pixabay.data.remote.dto.HitDTO
import com.marlen.pixabay.domain.models.ImageModel
import com.marlen.pixabay.domain.models.User


fun ImageEntity.mapToDomain() = ImageModel(
    id = id,
    remoteId = remoteId,
    webFormatURL = webFormatUrl,
    largeImageURL = largeImageURL,
    user = user.mapToDomain(),
    tags = tags,
    comments = comments,
    likes = likes,
    downloads = downloads,
    aspectRatio = aspectRatio,
)

fun UserEntity.mapToDomain() = User(
    name = name,
    avatar = avatar
)

// TODO подумать как избавиться от сплита .split(", ").toList()
fun HitDTO.mapToEntity(
    itemIndex: Int,
    pageIndex: Int,
    query: String
) = ImageEntity(
    remoteId = id,
    webFormatUrl = webformatURL,
    largeImageURL = largeImageURL,
    user = UserEntity(
        name = user,
        avatar = userImageURL
    ),
    tags = tags.split(", ").toList(),
    comments = comments,
    likes = likes,
    downloads = downloads,
    aspectRatio = imageWidth.toFloat() / imageHeight.toFloat(),
    itemIndex = itemIndex,
    pageIndex = pageIndex,
    searchQuery = query
)
