package com.vsee.news.data.remote.features.news.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.model.SourceItem
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class ArticleItemDto(
    @SerializedName("source")
    val source: SourceItemDto?,
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
): Parcelable, Dto {

    override fun mapToDomainModel(): Model {
        return ArticleItem(
            source = source?.mapToDomainModel() as SourceItem,
            title = title,
            author = author,
            url = url,
            urlToImage = urlToImage,
            description = description,
            publishedAt = publishedAt,
            content = content,
        )
    }
}