package com.marlen.pixabay.presentation

import androidx.navigation.NavController
import com.marlen.pixabay.presentation.details.DetailArgs
import com.marlen.pixabay.presentation.details.DetailsNavArgs
import com.marlen.pixabay.presentation.utils.toNavArgString

fun NavController.navigateToDetails(itemId: Int) {
    val route = HomeDestination.DETAIL.route.replace("{${DetailsNavArgs.detailArg}}", DetailArgs(itemId).toNavArgString())
    navigate(route)
}