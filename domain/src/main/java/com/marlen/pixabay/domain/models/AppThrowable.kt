package com.marlen.pixabay.domain.models

class AppThrowable(
    val error: Error,
    message: String?
) : Throwable(message)