package com.sliide.news.core.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.ButterKnife
import com.sliide.news.BR
import com.sliide.news.R
import com.sliide.news.network.model.NewsItem
import java.util.ArrayList

class NewsRecyclerViewAdapter(
    private val data: ArrayList<NewsItem>,
    private val pagerAdapter: ViewPagerAdapter?
) : RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder>() {

    lateinit var mBinding: ViewDataBinding
    lateinit var viewPager: ViewPager

    companion object {
        private const val VIEW_TYPE_RECYCLER_VIEW = 1
        private const val VIEW_TYPE_PAGER_INIT = 2
    }

    inner class NewsViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            mBinding = binding
        }

        fun bind(obj: Any) {
            mBinding.setVariable(BR.newsItem, obj)
            mBinding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding

        if (viewType == VIEW_TYPE_PAGER_INIT) {
            binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_pager,
                parent, false
            )
            ButterKnife.bind(this, binding.root)
            viewPager = binding.root.findViewById(R.id.viewPager)
            viewPager.adapter = pagerAdapter
        } else {
            binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_news_vertical_display,
                parent, false
            )
        }
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val actualPosition = getActualPosition(position)
        if (getItemViewType(position) == VIEW_TYPE_PAGER_INIT) {
            pagerAdapter?.addData(data.subList(actualPosition, actualPosition + 3))
            holder.bind(data[position])
        } else if (getItemViewType(position) == VIEW_TYPE_RECYCLER_VIEW) {
            val temperatureData = data[actualPosition]
            holder.bind(temperatureData)
        }
    }

    private fun getActualPosition(position: Int): Int {
        val numberOfPagerViews = position / 4
        val pagerOffset = numberOfPagerViews * 2
        return (position + pagerOffset)
    }

    override fun getItemViewType(position: Int): Int {
        val offset = position % 4
        return if (offset == 3) {
            VIEW_TYPE_PAGER_INIT
        } else {
            VIEW_TYPE_RECYCLER_VIEW
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
