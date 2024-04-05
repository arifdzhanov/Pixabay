package com.marlen.pixabay.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.marlen.pixabay.domain.models.User
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import com.marlen.pixabay.presentation.components.Avatar

@Composable
fun User(
    modifier: Modifier = Modifier,
    user: User,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Avatar(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape),
            avatarUrl = user.avatar,
            userName = user.name,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = user.name,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}