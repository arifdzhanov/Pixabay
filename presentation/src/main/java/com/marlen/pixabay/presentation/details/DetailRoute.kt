package com.marlen.pixabay.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.marlen.pixabay.presentation.R
import com.marlen.pixabay.presentation.components.AsyncImage
import com.marlen.pixabay.presentation.components.ErrorView
import com.marlen.pixabay.presentation.components.LoadingView
import com.marlen.pixabay.presentation.components.Tags
import com.marlen.pixabay.presentation.components.User
import com.marlen.pixabay.presentation.res.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailRoute(
    viewModel: DetailViewModel,
    onBackClick: () -> Unit,
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        TopAppBar(
            title = { Text(text = "Image Details") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            },
        )

        when (state) {
            is DetailState.Content -> ContentScreen(state)
            DetailState.Error      -> ErrorView(reload = viewModel::reload)
            DetailState.Loading    -> LoadingView()
        }

    }
}

@Composable
private fun ContentScreen(
    state: DetailState.Content
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimens.spacingNormal)
    ) {
        AsyncImage(
            modifier = Modifier
                .aspectRatio(state.image.aspectRatio)
                .fillMaxWidth()
                .wrapContentHeight(),
            url = state.image.largeImageURL,
            networkStatus = state.isInternetAvailable
        )

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        User(user = state.image.user)

        Spacer(modifier = Modifier.height(Dimens.spacingSmall))

        Text(text = stringResource(id = R.string.likes, state.image.likes))

        Spacer(modifier = Modifier.height(Dimens.spacingSmall))

        Text(text = stringResource(id = R.string.downloads, state.image.downloads))

        Spacer(modifier = Modifier.height(Dimens.spacingSmall))

        Text(text = stringResource(id = R.string.comments, state.image.comments))

        Spacer(modifier = Modifier.height(Dimens.spacingNormal))

        Text(text = "Tags")

        Tags(
            modifier = Modifier
                .padding(8.dp),
            tags = state.image.tags
        )
    }
}