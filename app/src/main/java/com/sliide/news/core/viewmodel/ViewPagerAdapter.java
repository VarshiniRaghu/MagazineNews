package com.sliide.news.core.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.viewpager.widget.PagerAdapter;

import com.sliide.news.R;
import com.sliide.news.network.model.NewsItem;

import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private List<NewsItem> result;

    public ViewPagerAdapter(List<NewsItem> item) {
        result = item;
    }
    @Override
    public int getCount() {
        return result.size();
    }
    public void addData(List<NewsItem> items) {
        this.result = items;
        notifyDataSetChanged();
    }
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewDataBinding layout = DataBindingUtil.inflate(LayoutInflater.from(collection.getContext()),
                R.layout.item_news_horizontal_display, collection, false);
        collection.addView(layout.getRoot());
        layout.setVariable(BR.newsItem,result.get(position));
        return layout.getRoot();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
    @Override
    public float getPageWidth(final int position) {
        return 0.6f;
    }

}
