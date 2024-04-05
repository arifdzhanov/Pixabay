package com.marlen.pixabay.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun AsyncImage(
    modifier: Modifier = Modifier,
    url: String,
    networkStatus: Boolean = true,

){
    val imageLoadingState = remember { mutableStateOf<AsyncImagePainter.State>(AsyncImagePainter.State.Empty) }
    var retryHash by remember { mutableStateOf(0) }

    LaunchedEffect(networkStatus) {
        if (networkStatus && imageLoadingState.value is AsyncImagePainter.State.Error){
            retryHash++
        }
    }

    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .setParameter("retry_hash", retryHash)
            .memoryCacheKey(url)
            .build(),
        onError = { imageLoadingState.value = it },
        onSuccess = { imageLoadingState.value = it },
    )
    Image(
        modifier = modifier
            .background(ShimmerBrush(targetValue = 1300f, showShimmer = imageLoadingState.value !is AsyncImagePainter.State.Success)),
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.Fit,
    )
}