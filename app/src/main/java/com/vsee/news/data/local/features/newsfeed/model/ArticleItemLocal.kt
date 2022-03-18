package com.vsee.news.data.local.features.newsfeed.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vsee.news.data.local.Constants
import com.vsee.news.domain.features.base.model.Dto
import com.vsee.news.domain.features.base.model.Model
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.model.SourceItem

@Entity(tableName = Constants.TABLE_ARTICLE_ITEM)
data class ArticleItemLocal(
    @PrimaryKey
    val title: String ="",
    val source: SourceItem?,
    val author: String? = "",
    val url: String? ="",
    val urlToImage: String? ="",
    val description: String? ="",
    val publishedAt: String? ="",
    val content: String? ="",
) : Dto {
    override fun mapToDomainModel(): Model {
        return ArticleItem(
            title = title,
            source = source,
            author = author,
            url = url,
            urlToImage = urlToImage,
            description = description,
            publishedAt = publishedAt,
            content = content,
        )
    }
}