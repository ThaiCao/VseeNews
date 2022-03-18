package com.vsee.news.presentation.features.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vsee.news.base.BaseViewModel
import com.vsee.news.utils.AppDispatchers
import kotlinx.coroutines.launch
import com.vsee.news.domain.features.Resource
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.usecase.FeedUseCaseManager
import kotlinx.coroutines.withContext

class FeedViewModel (private val feedUsecaseManager: FeedUseCaseManager, private val dispatchers: AppDispatchers) : BaseViewModel(){

    var listArticleItem = MediatorLiveData<Resource<List<ArticleItem>>>()
    var errorText = MutableLiveData<String>()
    var isShowProgress = MutableLiveData<Boolean>()

    open fun onGetFeedData()= viewModelScope.launch(dispatchers.main){
        var articleResponseSource: LiveData<Resource<List<ArticleItem>>>
        withContext(dispatchers.io) {
            articleResponseSource = feedUsecaseManager.getListFeedNewsUseCase(keySearch = "apple", from = "2022-03-17", to = "2022-03-17", sortBy = "popularity")
        }
        try {
            listArticleItem.addSource(articleResponseSource) {
                if (it.data?.isNotEmpty() == true) {
                    listArticleItem.value = it
                }
            }
        } catch (e: Exception) {
        }
    }
}