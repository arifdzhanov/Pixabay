package com.marlen.pixabay.presentation

import com.marlen.pixabay.presentation.details.DetailsNavArgs

enum class RootDestination(val route: String) {
    HOME("home")
}

enum class HomeDestination(val route: String) {
    SEARCH("search"),
    DETAIL("detail/{${DetailsNavArgs.detailArg}}")
}