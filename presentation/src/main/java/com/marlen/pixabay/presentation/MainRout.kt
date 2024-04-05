package com.marlen.pixabay.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.marlen.pixabay.presentation.details.detailNavGraph
import com.marlen.pixabay.presentation.search.searchNavGraph

@Composable
fun MainRoute() {
    val navController = rememberNavController()

    MainScreen(
        navController = navController
    )
}

@Composable
internal fun MainScreen(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = RootDestination.HOME.route,
    ) {
        navigation(
            route = RootDestination.HOME.route,
            startDestination = HomeDestination.SEARCH.route,
        ) {

            searchNavGraph(
                rootRoute = HomeDestination.SEARCH.route,
                navigateToDetail = { itemId: Int ->
                    navController.navigateToDetails(
                        itemId = itemId
                    )
                },
            )

            detailNavGraph(
                rootRoute = HomeDestination.DETAIL.route,
                onBackClick = {navController.popBackStack()},
            )
        }
    }
}