package com.sliide.news.core.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object ImageViewBinding {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, imageUrl: String) {
        Picasso.get()
            .load(imageUrl)
            .fit()
            .into(view)
    }
}
