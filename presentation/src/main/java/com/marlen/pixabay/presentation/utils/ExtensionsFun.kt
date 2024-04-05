package com.marlen.pixabay.presentation.utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.Base64

inline fun <reified T> T.toNavArgString(): String =
    Json.encodeToString(this).toUrlBase64()

inline fun <reified T> String.fromNavArgString(): T =
    Json.decodeFromString(this.fromUrlBase64())

fun String.toUrlBase64(): String {
    val originalByteArray = this.toByteArray()
    val encodedByteArray = Base64.getUrlEncoder().encode(originalByteArray)
    return String(encodedByteArray)
}

fun String.fromUrlBase64(): String {
    val originalByteArray = this.toByteArray()
    val decodedByteArray = Base64.getUrlDecoder().decode(originalByteArray)
    return String(decodedByteArray)
}