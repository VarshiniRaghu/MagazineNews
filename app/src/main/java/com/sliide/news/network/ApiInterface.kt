package com.sliide.news.network

import com.sliide.news.network.model.NewsResponseModel
import com.sliide.news.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("1/latest")
    suspend fun getNewsList(
        @Query("apikey") apiKey: String = BuildConfig.NEWS_API_KEY,
        @Query("language") language: String = "en"
    ): NewsResponseModel
}