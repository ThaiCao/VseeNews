package com.vsee.news.koin.modules

import com.vsee.news.data.remote.features.news.FeedDataSource
import org.koin.dsl.module

val remoteModule = module {
    factory { FeedDataSource(get()) }
}