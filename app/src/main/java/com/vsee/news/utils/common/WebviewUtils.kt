package com.vsee.news.utils.common

import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.util.*

object WebviewUtils {

    fun setUpWebView(webView: WebView,isLoadCache:Boolean)
    {
        webView.settings.allowFileAccess = true
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = if (isLoadCache) WebSettings.LOAD_CACHE_ELSE_NETWORK else  WebSettings.LOAD_DEFAULT
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
        webView.settings.domStorageEnabled = true

        webView.settings.loadWithOverviewMode = true
        webView.settings.databaseEnabled = true
    }


    fun getChromeClient(): WebChromeClient {
        return object : WebChromeClient() {

        }
    }

    fun getClient(): WebViewClient {
        return object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                return super.shouldInterceptRequest(view, request)
            }

            override fun shouldInterceptRequest(view: WebView, url: String?): WebResourceResponse? {
                try{
                    if(url == null){
                        return super.shouldInterceptRequest(view, url as String)
                    }
                    return if(url.toLowerCase(Locale.ROOT).contains(".jpg") || url.toLowerCase(
                            Locale.ROOT).contains(".jpeg")){
                        val bitmap = Glide.with(view).asBitmap().diskCacheStrategy(
                            DiskCacheStrategy.ALL).load(url).submit().get()
                        WebResourceResponse("image/jpg", "UTF-8",getBitmapInputStream(bitmap,
                            Bitmap.CompressFormat.JPEG))
                    }else if(url.toLowerCase(Locale.ROOT).contains(".png")){
                        val bitmap = Glide.with(view).asBitmap().diskCacheStrategy(
                            DiskCacheStrategy.ALL).load(url).submit().get()
                        WebResourceResponse("image/png", "UTF-8",getBitmapInputStream(bitmap,
                            Bitmap.CompressFormat.PNG))
                    }else if(url.toLowerCase(Locale.ROOT).contains(".webp")){
                        val bitmap = Glide.with(view).asBitmap().diskCacheStrategy(
                            DiskCacheStrategy.ALL).load(url).submit().get()
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                            WebResourceResponse("image/webp", "UTF-8",getBitmapInputStream(bitmap,
                                Bitmap.CompressFormat.WEBP_LOSSY))
                        } else {
                            WebResourceResponse("image/webp", "UTF-8",getBitmapInputStream(bitmap,
                                Bitmap.CompressFormat.WEBP))

                        }

                    }else{
                        super.shouldInterceptRequest(view, url)
                    }
                }catch (e:Exception){
                    return super.shouldInterceptRequest(view, url)
                }

            }
        }
    }

    fun getBitmapInputStream(bitmap: Bitmap, compressFormat: Bitmap.CompressFormat): InputStream {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(compressFormat, 80, byteArrayOutputStream)
        val bitmapData: ByteArray = byteArrayOutputStream.toByteArray()
        return ByteArrayInputStream(bitmapData)
    }

}