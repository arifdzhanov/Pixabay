package com.marlen.pixabay.presentation.search.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun ItemRefresh(
    modifier: Modifier = Modifier,
    isProgressing: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = isProgressing) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}