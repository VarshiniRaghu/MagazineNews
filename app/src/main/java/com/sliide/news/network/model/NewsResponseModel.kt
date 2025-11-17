package com.sliide.news.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsResponseModel(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null,

    @SerializedName("results")
    @Expose
    var content: ArrayList<NewsItem>? = null
)
