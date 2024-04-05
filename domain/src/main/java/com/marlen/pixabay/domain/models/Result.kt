package com.marlen.pixabay.domain.models

typealias RootError = Error

sealed interface Result<out D, out E> {

    data class Success<out D, out E>(
        val data: D,
    ) : Result<D, E>

    data class Error<out D, out E : RootError>(
        val error: RootError
    ) : Result<D, E>
}