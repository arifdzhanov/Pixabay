package com.marlen.pixabay.presentation.details

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.detailNavGraph(
    rootRoute: String,
    onBackClick: () -> Unit,
) {
    composable(
        route = rootRoute,
        arguments = DetailsNavArgs.navArgs
    ) {
        DetailRoute(
            viewModel = hiltViewModel(),
            onBackClick = onBackClick
        )
    }
}