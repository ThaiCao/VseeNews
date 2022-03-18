package com.vsee.news.data.repository.features.news

import androidx.lifecycle.LiveData
import com.vsee.news.BuildConfig
import com.vsee.news.data.local.features.newsfeed.dao.NewsFeedDao
import com.vsee.news.data.local.features.newsfeed.model.ArticleItemLocal
import com.vsee.news.data.remote.features.news.FeedDataSource
import com.vsee.news.data.remote.features.news.model.FeedResponseDto
import com.vsee.news.data.repository.NetworkWithCacheBoundResource
import com.vsee.news.domain.features.Resource
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.domain.features.news.repository.FeedRepository
import kotlinx.coroutines.Deferred

class FeedRepositoryImpl(
    private val feedDataSource: FeedDataSource,
    private val newsFeedDao: NewsFeedDao
) : FeedRepository {
    override suspend fun getFeedNewsAsync(keySearch: String, from: String, to: String, sortBy: String, apiKey: String): LiveData<Resource<List<ArticleItem>>> {
        return object : NetworkWithCacheBoundResource<List<ArticleItem>, FeedResponseDto>() {
            override fun processResponse(response: FeedResponseDto): List<ArticleItem> {
                return response?.articles?.map {
                    it.mapToDomainModel() as ArticleItem
                }?: arrayListOf()
            }

            override suspend fun saveCallResults(items: List<ArticleItem>) {
                newsFeedDao.deleteListArticle()
                items.map {
                    newsFeedDao.save(it.toLocalDto() as ArticleItemLocal)
                }
            }


            override suspend fun loadFromDb(): List<ArticleItem> {
                val dataLocal =
                    newsFeedDao.getListArticles()
                return dataLocal?.map {
                    it.mapToDomainModel() as ArticleItem
                } ?: listOf()

            }

            override fun createCallAsync(): Deferred<FeedResponseDto> {
                return feedDataSource.getListFeed(keySearch,from,to,sortBy, apiKey)
            }

            override fun shouldFetch(data: List<ArticleItem>?): Boolean {
                return true
            }

        }.build().asLiveData()
    }
}