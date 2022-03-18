package com.vsee.news.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.model.SourceItem

class Converter {

    @TypeConverter
    fun listArticleItemToJson(value: List<ArticleItem>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToListArticleItem(value: String): List<ArticleItem>? {
        val type = object : TypeToken<List<ArticleItem>>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun sourceItemToJson(value: SourceItem?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToSourceItem(value: String): SourceItem? {
        return Gson().fromJson(value, SourceItem::class.java)
    }
}