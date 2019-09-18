package com.sliide.news.network;


import com.sliide.news.network.model.NewsResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/309PryD")
    Observable<NewsResponseModel> getNewsList();


}
