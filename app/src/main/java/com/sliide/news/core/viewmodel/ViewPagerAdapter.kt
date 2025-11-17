package com.sliide.news.core.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sliide.news.BR
import androidx.viewpager.widget.PagerAdapter
import com.sliide.news.R
import com.sliide.news.network.model.NewsItem

class ViewPagerAdapter(private var result: List<NewsItem>) : PagerAdapter() {

    override fun getCount(): Int {
        return result.size
    }

    fun addData(items: List<NewsItem>) {
        this.result = items
        notifyDataSetChanged()
    }

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val layout = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(collection.context),
            R.layout.item_news_horizontal_display, collection, false
        )
        collection.addView(layout.root)
        layout.setVariable(BR.newsItem, result[position])
        return layout.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun getPageWidth(position: Int): Float {
        return 0.6f
    }
}
