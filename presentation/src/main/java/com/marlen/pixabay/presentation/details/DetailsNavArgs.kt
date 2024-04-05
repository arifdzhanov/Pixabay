package com.marlen.pixabay.presentation.details

import android.os.Bundle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.marlen.pixabay.presentation.utils.fromNavArgString
import kotlinx.serialization.Serializable

@Serializable
data class DetailsNavArgs(
    val args: DetailArgs
) {
    constructor(arguments: Bundle?) : this(args = (arguments?.getString(detailArg).orEmpty()).fromNavArgString())

    companion object {
        const val detailArg = "detailArg"

        val navArgs = listOf(
            navArgument(detailArg) {
                type = NavType.StringType
            }
        )
    }
}

@Serializable
data class DetailArgs(
    val imageId: Int
)