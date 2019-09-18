package com.sliide.news.core.datamodel;

import com.sliide.news.network.model.NewsItem;
import com.sliide.news.network.model.NewsResponseModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;

public interface INewsListDataModel {

    public Observable<NewsResponseModel> getNewsList();
}
