package com.sliide.news.core.datamodel;

import com.sliide.news.network.DaggerNetworkComponent;
import com.sliide.news.network.model.NewsResponseModel;

import io.reactivex.Observable;

public class NewsListDataModelImpl implements INewsListDataModel {


    @Override
    public Observable<NewsResponseModel> getNewsList() {
        return DaggerNetworkComponent.create().createApiInterface().getNewsList();
    }
}
