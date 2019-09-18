package com.sliide.news.core.viewmodel;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.sliide.news.core.datamodel.INewsListDataModel;
import com.sliide.news.core.datamodel.NewsListDataModelImpl;
import com.sliide.news.network.model.NewsItem;
import com.sliide.news.network.model.NewsResponseModel;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NewsListViewModel {

    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private ArrayList<NewsItem> result = new ArrayList<>();
    private ViewPagerAdapter pagerAdapter;


    protected NewsRecyclerViewAdapter getAdapter() {
        if (pagerAdapter == null) {
            pagerAdapter = new ViewPagerAdapter(result);
        }
        if (newsRecyclerViewAdapter == null) {
            newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(result, pagerAdapter);
        }
        return newsRecyclerViewAdapter;
    }


    protected RecyclerView.LayoutManager setLayoutManager() {
        if (getRecyclerView() != null) {
            getRecyclerView().addItemDecoration(new DividerItemDecoration(getRecyclerView().getContext(), LinearLayout.VERTICAL));
        }
        return new LinearLayoutManager(getRecyclerView().getContext());
    }

    private INewsListDataModel newsListDataModel;

    public NewsListViewModel() {
        newsListDataModel = new NewsListDataModelImpl();
        addListToView(null);
    }

    public void addListToView(View view) {

        newsListDataModel.getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<NewsResponseModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsResponseModel newsResponseModel) {
                        result.clear();
                        pagerAdapter = new ViewPagerAdapter(result);
                        result.addAll(newsResponseModel.content);
                        if (newsRecyclerViewAdapter != null) {
                            newsRecyclerViewAdapter.notifyDataSetChanged();
                            pagerAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public final void setupRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(getAdapter());
        this.recyclerView.setLayoutManager(setLayoutManager());
        this.recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        if (this.recyclerView.getItemAnimator() != null && this.recyclerView.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        }
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

}
