package com.vsee.news.data.remote.features.news.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.model.FeedResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
@Keep
data class FeedResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleItemDto>? = listOf(),
): Parcelable, Dto {
    override fun mapToDomainModel(): Model {
        return FeedResponse(
            articles = articles?.map{it.mapToDomainModel() as ArticleItem }
        )
    }

}