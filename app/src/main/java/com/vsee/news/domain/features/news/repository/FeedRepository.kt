package com.vsee.news.domain.features.news.repository

import androidx.lifecycle.LiveData
import com.vsee.news.domain.features.Resource
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.model.FeedResponse

interface FeedRepository {
    suspend fun getFeedNewsAsync(keySearch: String, from: String, to: String, sortBy: String, apiKey: String): LiveData<Resource<List<ArticleItem>>>

//    suspend fun getFeedNewsCacheAsync(): LiveData<Resource<List<FeedResponse>>>
}