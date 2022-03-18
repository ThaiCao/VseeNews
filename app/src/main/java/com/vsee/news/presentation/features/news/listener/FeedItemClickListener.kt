package com.vsee.news.presentation.features.news.listener

import com.vsee.news.domain.features.news.model.ArticleItem

interface FeedItemClickListener {
    fun onFeedItemClick(articleItem: ArticleItem)
}