package com.sliide.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sliide.news.core.viewmodel.NewsListViewModel
import com.sliide.news.databinding.ActivityMainBinding
import com.sliide.news.network.ApiInterface
import com.sliide.news.network.model.NewsItem
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var apiInterface: ApiInterface
    private val result: ArrayList<NewsItem> = ArrayList()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.newsListViewModel = NewsListViewModel()
    }
}