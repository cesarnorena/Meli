package com.cesarnorena.meli.library.retrofit

import com.cesarnorena.meli.BuildConfig
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectorFactory {

    inline fun <reified T> create(
        baseUrl: HttpUrl = BuildConfig.BASE_URL.toHttpUrl()
    ): T {
        val logging = HttpLoggingInterceptor()
            .setLevel(if (BuildConfig.DEBUG) Level.BASIC else Level.NONE)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}
