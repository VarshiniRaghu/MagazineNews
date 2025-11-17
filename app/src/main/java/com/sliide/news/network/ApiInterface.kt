package com.sliide.news.network

import com.sliide.news.network.model.NewsResponseModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {
    @GET("1/latest?apikey=pub_4a7e39447b314d858bc0d7023dc51814")
    fun getNewsList(): Observable<NewsResponseModel>
}
