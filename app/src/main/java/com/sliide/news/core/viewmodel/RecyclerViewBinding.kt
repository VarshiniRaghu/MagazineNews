package com.sliide.news.core.viewmodel

import android.view.MotionEvent
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewBinding {
    @JvmStatic
    @BindingAdapter("recyclerViewViewModel")
    fun setRecyclerViewViewModel(
        recyclerView: RecyclerView,
        viewModel: NewsListViewModel
    ) {
        viewModel.setupRecyclerView(recyclerView)

        // Fix issue of view pager scroll not working
        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return false
            }

            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                // Not implemented
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                // Not implemented
            }
        })
    }
}