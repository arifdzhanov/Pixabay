package com.marlen.pixabay.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Tags(
    modifier: Modifier = Modifier,
    tags: List<String>
) {
    FlowRow(
        modifier = modifier,
    ) {
        tags.forEach {
            Tag(
                modifier = Modifier
                    .padding(2.dp),
                tag = it,
            )
        }
    }
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    tag: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50f))
            .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f))
    ) {
        Text(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 6.dp),
            text = tag,
            style = MaterialTheme.typography.bodySmall
        )
    }
}