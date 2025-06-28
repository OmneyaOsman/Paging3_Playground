package com.omni.paging3playground.data.api

import com.omni.paging3playground.data.model.UnsplashDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {

    @GET("photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20
    ): List<UnsplashDto>

    companion object {
        fun create(): UnsplashApi {
            val logger = HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
            val client = OkHttpClient.Builder().addInterceptor { chain ->
                chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Client-ID ${CLIENT_ID}")
                    .build()
                    .let(chain::proceed)
            }.addInterceptor(logger).build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build()
                .create(UnsplashApi::class.java)
        }

        const val BASE_URL = "https://api.unsplash.com/"

        // IMPORTANT: Store your API Key securely and do not commit it to version control.
        // Consider using buildConfigField or a local.properties file.
        const val CLIENT_ID = "XKfd5m275FLNkMD9BToiiQ1P9ZMIygUmJxWqHy4gbRI"
    }
}
