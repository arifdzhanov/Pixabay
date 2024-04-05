package com.marlen.pixabay.presentation.search

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.searchNavGraph(
    rootRoute: String,
    navigateToDetail: (itemId: Int) -> Unit
) {
    composable(
        route = rootRoute,
    ) {
        SearchImageRoute(
            navigateToDetail = navigateToDetail
        )
    }
}