package com.marlen.pixabay.presentation.search.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.marlen.pixabay.presentation.R
import com.marlen.pixabay.presentation.components.GifIcons
import com.marlen.pixabay.presentation.components.Retry
import com.marlen.pixabay.presentation.res.Dimens

@Composable
internal fun ItemError(
    modifier: Modifier = Modifier,
    reload: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(Dimens.spacingBig))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.error_message_from_cache),
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Button(onClick = reload) {
            Row {
                GifIcons.Retry()
                Spacer(modifier = Modifier.width(Dimens.spacingNormal))
                Text(text = stringResource(id = R.string.retry))
            }
        }

        Spacer(modifier = Modifier.height(Dimens.spacingBig))
    }
}