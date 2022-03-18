package com.vsee.news.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vsee.news.data.local.converter.Converter
import com.vsee.news.data.local.features.newsfeed.dao.NewsFeedDao
import com.vsee.news.data.local.features.newsfeed.model.ArticleItemLocal


@Database(
    entities = [
        ArticleItemLocal::class,
    ],
    version =1,
    exportSchema = false
)
@TypeConverters(
    Converter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun newsFeedDao(): NewsFeedDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "vseenews.db")
                .fallbackToDestructiveMigration()
                .build()
    }

}