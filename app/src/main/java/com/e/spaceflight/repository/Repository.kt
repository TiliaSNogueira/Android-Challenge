package com.e.spaceflight.repository

import com.e.spaceflight.ArticleDao
import com.e.spaceflight.model.Article
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Service {

    //_limit => quantidade de articles retornados (15)
    //_start => quantidade de articles pulados quando faz requisição (para paginação)

    // @GET("api/v3/articles")
    @GET("articles")
    suspend fun getArticlesAPI(
        @Query("_limit") _limit: Int,
        @Query("_start") _start: Int
    ): ArrayList<Article>
}

var client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(100, TimeUnit.SECONDS)
    .readTimeout(100, TimeUnit.SECONDS).build()

val retrofit: Retrofit = Retrofit.Builder()
    //.baseUrl("https://spaceflightnewsapi.net/")
    .baseUrl("https://api.spaceflightnewsapi.net/v3/")
    .client(client)
    .addConverterFactory(GsonConverterFactory.create()).build()

val service: Service = retrofit.create(Service::class.java)

