package com.sliide.news.network.model

import com.google.gson.annotations.SerializedName

class NewsItem {
    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var summary: String? = null

    @SerializedName("image_url")
    var imageUrl: String? = null

    @SerializedName("content")
    var content: String? = null

    @SerializedName("pubDate")
    var pubDate: String? = null

    @SerializedName("source_id")
    var sourceId: String? = null

    @SerializedName("link")
    var link: String? = null

    // Maintain backward compatibility with existing code
    var images: Image? = null

    // Keep this class for backward compatibility
    class Image {
        var mainImage: MainImage? = null

        class MainImage {
            var url: String? = null
        }
    }
}
