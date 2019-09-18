package com.sliide.news;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sliide.news.core.viewmodel.NewsListViewModel;
import com.sliide.news.databinding.ActivityMainBinding;
import com.sliide.news.network.ApiInterface;
import com.sliide.news.network.model.NewsItem;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private ArrayList<NewsItem> result = new ArrayList<>();
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setNewsListViewModel(new NewsListViewModel());
    }


}
