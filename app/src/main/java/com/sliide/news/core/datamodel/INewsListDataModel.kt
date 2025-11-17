package com.sliide.news.core.datamodel

import com.sliide.news.network.model.NewsResponseModel
import io.reactivex.Observable

interface INewsListDataModel {
    fun getNewsList(): Observable<NewsResponseModel>
}