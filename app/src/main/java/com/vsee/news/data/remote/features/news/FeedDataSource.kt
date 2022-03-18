package com.vsee.news.data.remote.features.news

class FeedDataSource (private val feedServiceApi: IFeedServiceApi) {
    fun getListFeed( keySearch: String, from: String, to: String, sortBy: String, apiKey: String ) = feedServiceApi.getFeed(keySearch = keySearch, from = from, to = to, sortBy = sortBy, apiKey = apiKey)
}