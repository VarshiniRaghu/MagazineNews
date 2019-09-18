package com.sliide.news.core.viewmodel;

import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewBinding {

    @BindingAdapter( "recyclerViewViewModel" )
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                NewsListViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);

        //fix issue of view pager scroll not working
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }
}
