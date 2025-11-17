package com.sliide.news.core.datamodel

import com.sliide.news.network.NetworkComponent
import com.sliide.news.network.model.NewsResponseModel
import io.reactivex.Observable
import javax.inject.Inject

class NewsListDataModelImpl : INewsListDataModel {
    @Inject
    lateinit var networkComponent: NetworkComponent

    init {
        // In a real app, you would use dependency injection here
        // For now, we'll use reflection to get the generated DaggerNetworkComponent
        try {
            val daggerNetworkComponentClass = Class.forName("com.sliide.news.network.DaggerNetworkComponent")
            val createMethod = daggerNetworkComponentClass.getMethod("create")
            networkComponent = createMethod.invoke(null) as NetworkComponent
        } catch (e: Exception) {
            throw RuntimeException("Error creating NetworkComponent", e)
        }
    }

    override fun getNewsList(): Observable<NewsResponseModel> {
        return networkComponent.createApiInterface().getNewsList()
    }
}
