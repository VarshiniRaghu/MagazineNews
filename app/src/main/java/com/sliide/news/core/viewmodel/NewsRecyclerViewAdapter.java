package com.sliide.news.core.viewmodel;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.sliide.news.R;
import com.sliide.news.network.model.NewsItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {
    private ArrayList<NewsItem> data;
    public ViewDataBinding mBinding;
    private ViewPagerAdapter pagerAdapter;
    private static int VIEW_TYPE_RECYCLER_VIEW = 1;
    private static int VIEW_TYPE_PAGER_INIT = 2;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        public NewsViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
        public void bind(Object obj) {
            mBinding.setVariable(BR.newsItem,obj);
            mBinding.executePendingBindings();
        }
    }
    public NewsRecyclerViewAdapter(ArrayList<NewsItem> myDataset, ViewPagerAdapter pagerAdapter) {
        data = myDataset;
        this.pagerAdapter = pagerAdapter;
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding;
        if(viewType == VIEW_TYPE_PAGER_INIT) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_pager,
                    parent, false);
            ButterKnife.bind(this, binding.getRoot());
            viewPager.setAdapter(pagerAdapter);
        } else{
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_news_vertical_display,
                    parent, false);
        }
        return new NewsViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        int actualPosition = getActualPosition(position);
        if(getItemViewType(position) == VIEW_TYPE_PAGER_INIT) {
            pagerAdapter.addData(data.subList(actualPosition,actualPosition + 3));
            holder.bind(data.get(position));
        } else if(getItemViewType(position) == VIEW_TYPE_RECYCLER_VIEW){
            final NewsItem temperatureData = data.get(actualPosition);
            holder.bind(temperatureData);
        }
    }

    private int getActualPosition(int position) {
        int numberOfPagerViews = position/4;
        int pagerOffset = numberOfPagerViews * 2;
        return (position + pagerOffset);
    }

    @Override
    public int getItemViewType(int position) {
        int offset = position % 4;
        if(offset == 3) {
            return VIEW_TYPE_PAGER_INIT;
        }
        return VIEW_TYPE_RECYCLER_VIEW;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
