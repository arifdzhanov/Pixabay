package com.marlen.pixabay.presentation.search.model

import com.marlen.pixabay.domain.models.ImageModel
import com.marlen.pixabay.domain.models.User

data class ImageUIModel(
    val id: Int,
    val remoteId: Int,
    val url: String,
    val user: User,
    val tags: List<String>,
    val aspectRatio: Float,
)

private const val DISPLAY_TAGS_MAC_COUNT = 3

internal fun ImageModel.toUIModel(maxImageWidth: Int): ImageUIModel {

    val formattedTags = this.tags.sortedByDescending { it.length }.let { sortedTags ->
        if (sortedTags.size > DISPLAY_TAGS_MAC_COUNT) {
            val tagsTakeCount = DISPLAY_TAGS_MAC_COUNT - 1
            sortedTags.take(tagsTakeCount)
                .toMutableList()
                .apply { add("+${sortedTags.size - tagsTakeCount}") }
        } else {
            sortedTags
        }
    }

    return ImageUIModel(
        id = id,
        remoteId = remoteId,
        url = this.buildPreviewImageUrl(maxImageWidth),
        user = user,
        tags = formattedTags,
        aspectRatio = aspectRatio
    )
}

private const val IMAGE_PREFIX_180 = "_180"
private const val IMAGE_PREFIX_340 = "_340"
private const val IMAGE_PREFIX_640 = "_640"
private const val IMAGE_PREFIX_960 = "_960"
private const val IMAGE_PREFIX_1280 = "_1280"

private const val IMAGE_SIZE_180 = 180
private const val IMAGE_SIZE_340 = 340
private const val IMAGE_SIZE_640 = 640
private const val IMAGE_SIZE_960 = 960
private const val IMAGE_SIZE_1280 = 1280

private fun ImageModel.buildPreviewImageUrl(imageMaxWidth: Int): String {
    val imageMaxHeight = (imageMaxWidth * this.aspectRatio).toInt()

    val imageMaxSide = if (imageMaxHeight > imageMaxWidth)
        imageMaxHeight
    else
        imageMaxWidth

    val sizeSuffix = when {
        imageMaxSide <= IMAGE_SIZE_180 -> IMAGE_PREFIX_180
        imageMaxSide <= IMAGE_SIZE_340 -> IMAGE_PREFIX_340
        imageMaxSide <= IMAGE_SIZE_640 -> IMAGE_PREFIX_640
        imageMaxSide <= IMAGE_SIZE_960 -> IMAGE_PREFIX_960
        else                           -> IMAGE_PREFIX_1280
    }

    return this.largeImageURL.replace(IMAGE_PREFIX_1280, sizeSuffix)
}
