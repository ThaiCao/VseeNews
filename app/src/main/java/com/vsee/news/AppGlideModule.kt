package com.vsee.news

import android.content.Context
import android.graphics.drawable.PictureDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.vsee.news.utils.glide.UnsafeOkHttpClient.getUnsafeOkHttpClient
import okhttp3.OkHttpClient
import java.io.InputStream

@GlideModule
class AppGlideModule : AppGlideModule() {


    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okHttpClient: OkHttpClient? = getUnsafeOkHttpClient()
        okHttpClient?.let{
            registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okHttpClient))
        }
    }


    // Disable manifest parsing to avoid adding similar modules twice.
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}