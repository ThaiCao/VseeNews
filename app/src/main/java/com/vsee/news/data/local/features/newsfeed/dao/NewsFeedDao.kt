package com.vsee.news.data.local.features.newsfeed.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vsee.news.data.local.dao.BaseDao
import com.vsee.news.data.local.features.newsfeed.model.ArticleItemLocal

@Dao
abstract class NewsFeedDao : BaseDao<ArticleItemLocal>(){

    suspend fun save(article: ArticleItemLocal) {
        insert(article)
    }

    suspend fun save(articles: List<ArticleItemLocal>) {
        insert(articles)
    }

    @Transaction
    @Query("SELECT * FROM tbl_article_item WHERE title = :title")
    abstract suspend fun getArticleItem(title:String): List<ArticleItemLocal>?

    @Transaction
    @Query("SELECT * FROM tbl_article_item ")
    abstract suspend fun getListArticles(): List<ArticleItemLocal>?

    @Query("DELETE FROM tbl_article_item  WHERE title = :title")
    abstract suspend fun deleteArticleItem(title:String)

    @Query("DELETE FROM tbl_article_item")
    abstract suspend fun deleteListArticle()
}