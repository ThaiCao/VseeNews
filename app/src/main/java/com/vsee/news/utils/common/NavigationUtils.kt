package com.vsee.news.utils.common

import android.content.Context
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.newdetail.activity.FeedDetailActivity

object NavigationUtils {

    fun navigateToFeedDetailActivity(context: Context, articleItem: ArticleItem) {
        context.startActivity(FeedDetailActivity.newInstance(context, articleItem))
    }
}