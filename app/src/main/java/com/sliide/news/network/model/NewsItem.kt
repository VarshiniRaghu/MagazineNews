package com.sliide.news.network.model

import com.google.gson.annotations.SerializedName

data class NewsItem(
    @SerializedName("title")
    val title: String? = null,

    @SerializedName("description")
    val summary: String? = null,

    @SerializedName("image_url")
    val imageUrl: String? = null,

    @SerializedName("content")
    val content: String? = null,

    @SerializedName("pubDate")
    val pubDate: String? = null,

    @SerializedName("source_id")
    val sourceId: String? = null,

    @SerializedName("link")
    val link: String? = null
)