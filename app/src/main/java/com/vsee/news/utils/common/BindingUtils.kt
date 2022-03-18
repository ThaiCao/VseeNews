package com.vsee.news.utils.common

import android.graphics.drawable.Drawable
import android.view.KeyEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

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
fun setNewsTime(textView: TextView, time: String?) {
    try {
        textView.text = time?.let { DateTimeUtils.convertUTCToLocalString(it) }
    } catch (ignored: Exception) {
    }
}

@BindingAdapter("articleUrl")
fun setArticleUrl(webView: WebView, url: String?) {
    try {
       WebviewUtils.setUpWebView(webView,true)
        webView.apply {
            webChromeClient = WebviewUtils.getChromeClient()
            webViewClient = WebviewUtils.getClient()
        }
        webView.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }

        webView.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action === KeyEvent.ACTION_DOWN) {
                    val webView = v as WebView
                    when (keyCode) {
                        KeyEvent.KEYCODE_BACK -> if (webView.canGoBack()) {
                            webView.goBack()
                            return true
                        }
                    }
                }
                return false
            }
        })
        url?.let { webView.loadUrl(it) }
    } catch (ignored: Exception) {
    }
}