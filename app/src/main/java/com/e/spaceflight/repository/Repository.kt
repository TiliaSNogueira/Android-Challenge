package com.e.spaceflight.repository

import com.e.spaceflight.model.Article
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {

    @GET("api/v2/articles")
    suspend fun getArticlesAPI(
             @Query("_limit") _limit: Int,
            @Query("_start") _start: Int
    ): ArrayList<Article>
}

var client = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS).build()

val retrofit = Retrofit.Builder()
        .baseUrl("https://spaceflightnewsapi.net/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

val service: Service = retrofit.create(Service::class.java)



