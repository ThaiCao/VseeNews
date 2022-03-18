package com.vsee.news.data.remote.features.news

import com.vsee.news.data.remote.ApiEndPoint
import com.vsee.news.data.remote.features.news.model.FeedResponseDto
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface IFeedServiceApi {

    @GET(ApiEndPoint.NEW_FEED)
    fun getFeed(
        @Query("q") keySearch: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Deferred<FeedResponseDto>
}