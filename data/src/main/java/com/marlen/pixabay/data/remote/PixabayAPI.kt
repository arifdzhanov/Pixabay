package com.marlen.pixabay.data.remote

import com.marlen.pixabay.data.remote.dto.SearchImagesResponseDTO
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET(".")
    suspend fun searchImages(
        @Query("key") key: String,
        @Query("q") query: String,
        @Query("page") page: Int,
    ): SearchImagesResponseDTO
}

fun PixabayAPI(
    baseUrl: String,
    client: OkHttpClient = OkHttpClient.Builder().build(),
    converterFactory :Converter.Factory,
): PixabayAPI {
    val retrofit = createRetrofit(baseUrl, client, converterFactory)
    return retrofit.create()
}

private fun createRetrofit(
    baseUrl: String,
    client: OkHttpClient,
    converterFactory:Converter.Factory
): Retrofit {
    return Retrofit.Builder()
        .client(client)
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .build()
}