package com.vsee.news.domain.features.news.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vsee.news.data.local.features.newsfeed.model.ArticleItemLocal
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class ArticleItem(
    @SerializedName("source")
    val source: SourceItem?,
    @SerializedName("title")
    val title: String? ="",
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("url")
    val url: String? ="",
    @SerializedName("urlToImage")
    val urlToImage: String? ="",
    @SerializedName("description")
    val description: String? ="",
    @SerializedName("publishedAt")
    val publishedAt: String? ="",
    @SerializedName("content")
    val content: String? ="",
): Parcelable, Model {
    override fun toLocalDto(): Dto {
        return ArticleItemLocal(
            source = source,
            title = title?.let{it}?:"",
            author = author,
            url = url,
            urlToImage = urlToImage,
            description = description,
            publishedAt = publishedAt,
            content = content,
        )
    }

    override fun toRemoteDto(): Dto {
        TODO("Not yet implemented")
    }


}