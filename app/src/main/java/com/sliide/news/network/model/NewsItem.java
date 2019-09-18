package com.sliide.news.network.model;

import com.google.gson.annotations.SerializedName;

public class NewsItem{

    @SerializedName("summary")
    public String summary;

    public String getImageUrl() {
        if(images != null && images.mainImage != null && images.mainImage.url != null) {
            return images.mainImage.url;
        }
        return "";
    }

    @SerializedName("images")
    public Image images;

    private class Image {
        @SerializedName("mainImage")
        public MainImage mainImage;

        private class MainImage {
            @SerializedName("url")
            public String url;
        }
    }
}
