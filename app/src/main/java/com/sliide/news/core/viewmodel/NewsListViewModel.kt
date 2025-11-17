package com.sliide.news.core.viewmodel

import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sliide.news.core.datamodel.INewsListDataModel
import com.sliide.news.core.datamodel.NewsListDataModelImpl
import com.sliide.news.network.model.NewsItem
import com.sliide.news.network.model.NewsResponseModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class NewsListViewModel {
    private var recyclerView: RecyclerView? = null
    private var newsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null
    private val result: ArrayList<NewsItem> = ArrayList()
    private var pagerAdapter: ViewPagerAdapter? = null

    protected fun getAdapter(): NewsRecyclerViewAdapter {
        if (pagerAdapter == null) {
            pagerAdapter = ViewPagerAdapter(result)
        }
        if (newsRecyclerViewAdapter == null) {
            newsRecyclerViewAdapter = NewsRecyclerViewAdapter(result, pagerAdapter!!)
        }
        return newsRecyclerViewAdapter!!
    }

    protected fun setLayoutManager(): RecyclerView.LayoutManager {
        getRecyclerView()?.addItemDecoration(
            DividerItemDecoration(
                getRecyclerView()!!.context,
                LinearLayout.VERTICAL
            )
        )
        return LinearLayoutManager(getRecyclerView()!!.context)
    }

    private val newsListDataModel: INewsListDataModel

    init {
        newsListDataModel = NewsListDataModelImpl()
        addListToView(null)
    }

    fun addListToView(view: View?) {
        newsListDataModel.getNewsList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<NewsResponseModel> {
                override fun onSubscribe(d: Disposable) {
                    // Not implemented
                }

                override fun onNext(newsResponseModel: NewsResponseModel) {
                    result.clear()
                    pagerAdapter = ViewPagerAdapter(result)
                    newsResponseModel.content?.let { result.addAll(it) }
                    newsRecyclerViewAdapter?.let {
                        it.notifyDataSetChanged()
                        pagerAdapter?.notifyDataSetChanged()
                    }
                }

                override fun onError(e: Throwable) {
                    // Not implemented
                }

                override fun onComplete() {
                    // Not implemented
                }
            })
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        this.recyclerView?.setHasFixedSize(true)
        this.recyclerView?.adapter = getAdapter()
        this.recyclerView?.layoutManager = setLayoutManager()
        this.recyclerView?.overScrollMode = View.OVER_SCROLL_NEVER
        if (this.recyclerView?.itemAnimator is SimpleItemAnimator) {
            (this.recyclerView?.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    fun getRecyclerView(): RecyclerView? {
        return recyclerView
    }
}