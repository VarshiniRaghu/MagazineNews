package com.sliide.news.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsResponseModel {
    @SerializedName("content")
    @Expose
    public ArrayList<NewsItem> content;
}
