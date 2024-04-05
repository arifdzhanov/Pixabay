package com.marlen.pixabay.presentation.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.marlen.pixabay.presentation.R
import com.marlen.pixabay.presentation.components.ErrorView
import com.marlen.pixabay.presentation.search.component.ItemError
import com.marlen.pixabay.presentation.search.component.ItemImage
import com.marlen.pixabay.presentation.search.component.ItemRefresh
import com.marlen.pixabay.presentation.search.component.OpenDetailDialog
import com.marlen.pixabay.presentation.search.component.SearchView
import com.marlen.pixabay.presentation.search.component.fillWidthItem
import com.marlen.pixabay.presentation.search.model.ImageUIModel
import com.marlen.pixabay.presentation.res.Dimens


@Composable
fun SearchImageRoute(
    navigateToDetail: (itemId: Int) -> Unit,
) {
    val viewModel  = hiltViewModel<SearchViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value

    val navigationEvent by viewModel.navigationEvent.collectAsState()

    LaunchedEffect(navigationEvent) {
        when (val even = navigationEvent) {
            is SearchNavigationEvent.NavigateToDetail -> {
                navigateToDetail(even.imageId)
            }

            null                                      -> Unit
        }
        viewModel.resetNavigationEvent()
    }

    SearchImages(
        state = state,
        onSearchQueryChange = viewModel::searchQueryChange,
        clearClick = viewModel::clearSearchClick,
        orientationChanged = viewModel::orientationChanged,
        imageClick = viewModel::imageClick,
        dialogDismiss = viewModel::dialogDismiss,
        dialogConfirm = viewModel::dialogConfirm,
    )
}


@Composable
fun SearchImages(
    state: SearchViewState,
    onSearchQueryChange: (String) -> Unit,
    clearClick: () -> Unit,
    orientationChanged: () -> Unit,
    imageClick: (ImageUIModel) -> Unit,
    dialogDismiss: (SearchViewDialog) -> Unit,
    dialogConfirm: (SearchViewDialog) -> Unit,
) {

    val images = state.images.collectAsLazyPagingItems()
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration) {
        snapshotFlow { configuration.orientation }
            .collect { orientationChanged() }
    }
    LaunchedEffect(state.isInternetAvailable) {
        if (state.isInternetAvailable){
            if (images.loadState.refresh is LoadState.Error){
                images.refresh()
            }
            if (images.loadState.append is LoadState.Error){
                images.retry()
            }
        }
    }

    when (val dialog = state.dialog) {
        is SearchViewDialog.OpenImageDetailViewDialog -> {
            OpenDetailDialog(
                dialogTitle = stringResource(id = R.string.details_title),
                dialogText = stringResource(id = R.string.details_description),
                onDismissRequest = { dialogDismiss(dialog) },
                onConfirmation = { dialogConfirm(dialog) },
            )
        }

        null                                          -> {}
    }

    Column(
        modifier = Modifier
            .padding(top = Dimens.spacingBig)
            .padding(horizontal = Dimens.spacingNormal)
    ) {

        SearchView(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1.0f),
            value = state.searchQuery,
            onValueChange = onSearchQueryChange,
            placeholder = stringResource(id = R.string.search_placeholder),
            clearClick = clearClick
        )

        ItemRefresh(
            modifier = Modifier
                .height(Dimens.spacingBig),
            isProgressing = images.loadState.refresh is LoadState.Loading
        )

        AnimatedVisibility(visible = images.loadState.refresh is LoadState.Error && images.itemCount != 0) {
            ItemError(
                modifier = Modifier,
                reload = { images.refresh() },
            )
        }

        if (images.loadState.refresh is LoadState.Error && images.itemCount == 0) {
            ErrorView(
                modifier = Modifier.fillMaxSize(),
                reload = {
                    images.refresh()
                }
            )
        } else {
            LazyVerticalStaggeredGrid(
                modifier = Modifier,
                columns = StaggeredGridCells.Fixed(state.cellsCount),
                contentPadding = PaddingValues(bottom = Dimens.spacingNormal),
                verticalItemSpacing = Dimens.spacingNormal,
                horizontalArrangement = Arrangement.spacedBy(Dimens.spacingNormal)
            ) {

                items(
                    count = images.itemCount,
                    key = images.itemKey { it.remoteId },
                ) { index ->
                    images[index]?.let { item -> ItemImage(
                        image = item,
                        imageClick = imageClick,
                        networkStatus = state.isInternetAvailable,
                    ) }
                }

                if (images.loadState.append is LoadState.Loading) {
                    fillWidthItem { ItemRefresh() }
                }
                if (images.loadState.append is LoadState.Error) {
                    fillWidthItem {
                        ItemError(reload = {
                            images.retry()
                        })
                    }
                }
            }
        }
    }
}
