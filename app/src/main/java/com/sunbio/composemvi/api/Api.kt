package com.sunbio.composemvi.api

import android.util.Log
import com.sunbio.composemvi.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    /**
     * https://api.wmdb.tv/api/v1/top?type=Imdb&skip=0&limit=50&lang=Cn
     */
    @GET("api/v1/top")
    suspend fun getTop(
        @Query("type")
        type: String,
        @Query("skip")
        skip: Int,
        @Query("limit")
        limit: Int,
        @Query("lang")
        lang: String = "Cn"
    ): List<MovieItem>
}

private val okHttpClient = OkHttpClient.Builder()
    .apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor {
                Log.d("net", it)
            }.setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.wmdb.tv/")
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object ApiService : Api by retrofit.create(Api::class.java)
