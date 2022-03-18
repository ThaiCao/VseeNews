package com.vsee.news.utils.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter(value = *arrayOf("drawableId", "placeholder"), requireAll = false)
fun setDrawable(imageView: ImageView, drawableId: Int, placeholder: Drawable?) {
    val target = Glide.with(imageView.context).asBitmap().load(if (drawableId != 0) drawableId else null)
    if (placeholder != null) {
        target.placeholder(placeholder).error(placeholder)
    }
    target.into(imageView)
}

@BindingAdapter(value = *arrayOf("imageUrl", "placeholder"), requireAll = false)
fun setImageUrl(imageView: ImageView, imageUrl: String?, placeholder: Drawable?) {
    val target = Glide.with(imageView.context).asBitmap().load(imageUrl)
    if (placeholder != null) {
        target.placeholder(placeholder).error(placeholder)
    }
    target.into(imageView)
}

@BindingAdapter("newTime")
fun setNewsTime(textView: TextView, time: String) {
    try {
        textView.text = DateTimeUtils.convertUTCToLocalString(time)
    } catch (ignored: Exception) {
    }
}