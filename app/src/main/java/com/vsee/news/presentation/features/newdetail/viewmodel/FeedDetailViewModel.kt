package com.vsee.news.presentation.features.newdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.vsee.news.base.BaseViewModel
import com.vsee.news.domain.features.news.model.ArticleItem

class FeedDetailViewModel : BaseViewModel(){

    var articleItem = MutableLiveData<ArticleItem>()

    fun onUpdateArticleItem(article: ArticleItem){
        articleItem.value = article
    }
}