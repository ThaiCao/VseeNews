package com.vsee.news.domain.features.news.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.vsee.news.BuildConfig
import com.vsee.news.domain.features.Resource
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.repository.FeedRepository

class GetListFeedNewsUseCase (private val repository: FeedRepository) {

    suspend operator fun invoke(keySearch: String, from: String, to: String, sortBy: String): LiveData<Resource<List<ArticleItem>>> {
        return Transformations.map(repository.getFeedNewsAsync(keySearch, from, to, sortBy, BuildConfig.API_KEY)) {
            it }
    }
}
