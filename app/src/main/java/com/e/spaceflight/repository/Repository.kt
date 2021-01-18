package com.e.spaceflight.repository

import com.e.spaceflight.model.Article
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    @GET("api/v2/articles")
    suspend fun getArticlesAPI(
           // @Query("_limit") _limit: Int,
            @Query("_start") _start: Int,
    ): ArrayList<Article>
}


val retrofit = Retrofit.Builder()
        .baseUrl("https://spaceflightnewsapi.net/")
        .addConverterFactory(GsonConverterFactory.create()).build()

val service: Service = retrofit.create(Service::class.java)



