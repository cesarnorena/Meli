package com.cesarnorena.meli.library.retrofit

import com.cesarnorena.meli.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ConnectorFactory {

    val retrofit: Retrofit by lazy {
        val level = if (BuildConfig.DEBUG) Level.BASIC else Level.NONE
        val logging = HttpLoggingInterceptor().setLevel(level)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inline fun <reified T> get(): T = retrofit.create(T::class.java)
}
