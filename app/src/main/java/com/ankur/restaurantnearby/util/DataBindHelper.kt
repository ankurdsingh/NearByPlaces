package com.ankur.restaurantnearby.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ankur.restaurantnearby.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("android:rating")
fun setRating(textView: TextView, double: Double){
    textView.text = double.toString()
}

@BindingAdapter("android:loadImage")
fun loadImageSrc(imageView: ImageView, url: String?){
    val options = RequestOptions()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher_round)
    url?.let {
        Glide.with(imageView.context)
            .setDefaultRequestOptions(options)
            .load(it)
            .into(imageView)
    }
}