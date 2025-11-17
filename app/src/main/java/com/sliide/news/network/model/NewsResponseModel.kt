package com.sliide.news.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("content")
    @Expose
    var content: ArrayList<NewsItem>? = null
)