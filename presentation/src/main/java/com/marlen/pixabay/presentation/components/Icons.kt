package com.marlen.pixabay.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object GifIcons

@Composable
fun GifIcons.IcSearch(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified,
) {
    Icon(
        imageVector = Icons.Outlined.Search,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint,
    )
}
@Composable
fun GifIcons.Info(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Info,
        contentDescription = contentDescription,
        tint = tint,
    )
}

@Composable
fun GifIcons.Retry(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Refresh,
        contentDescription = contentDescription,
        tint = tint,
    )
}

@Composable
fun GifIcons.IcClose(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Close,
        contentDescription = contentDescription,
        tint = tint,
    )
}

@Composable
fun GifIcons.IcDelete(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Outlined.Delete,
        contentDescription = contentDescription,
        tint = tint,
    )
}