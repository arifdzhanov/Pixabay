package com.marlen.pixabay.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.marlen.pixabay.presentation.R
import java.util.Locale

@Composable
fun Avatar(
    modifier: Modifier = Modifier,
    avatarUrl: String,
    userName: String
) {
    val loaded = remember { mutableStateOf(false) }
    Box(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clip(CircleShape),
    ) {
        AsyncImage(
            model = avatarUrl,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.avatar_content_description),
            onSuccess = { loaded.value = true },
        )
        if (!loaded.value) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center),
                text = userName.take(1).uppercase(Locale.getDefault()),
                style = MaterialTheme.typography.labelSmall
            )
        }
    }

}