package com.vsee.news.utils.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object DrawableUtils {
    fun onLoadImageFromServer(context: Context, imageView: ImageView, imageUrl: String){
        Glide
            .with(context)
            .load(imageUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
//        Glide.with(context).asBitmap().load(imageUrl).into(imageView)
    }
}