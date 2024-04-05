package com.marlen.pixabay.presentation.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.marlen.pixabay.presentation.components.AsyncImage
import com.marlen.pixabay.presentation.components.Tags
import com.marlen.pixabay.presentation.components.User
import com.marlen.pixabay.presentation.search.model.ImageUIModel

@Composable
internal fun ItemImage(
    image: ImageUIModel,
    imageClick: (ImageUIModel) -> Unit,
    networkStatus: Boolean,
) {
    Column {
        Box {
            AsyncImage(
                modifier = Modifier
                    .clickable { imageClick(image) }
                    .aspectRatio(image.aspectRatio)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .border(width = 1.dp, MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f), RoundedCornerShape(12.dp)),
                url = image.url,
                networkStatus = networkStatus
            )

            Tags(
                modifier = Modifier
                    .padding(8.dp),
                tags = image.tags
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        User(
            modifier = Modifier
                .padding(start = 12.dp),
            user = image.user
        )
    }
}




