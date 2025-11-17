package com.sliide.news.network.model

import com.google.gson.annotations.SerializedName

class NewsItem {
    @SerializedName("summary")
    var summary: String? = null

    @SerializedName("images")
    var images: Image? = null

    fun getImageUrl(): String {
        return if (images?.mainImage?.url != null) {
            images?.mainImage?.url!!
        } else {
            ""
        }
    }

    class Image {
        @SerializedName("mainImage")
        var mainImage: MainImage? = null

        class MainImage {
            @SerializedName("url")
            var url: String? = null
        }
    }
}
