package com.vsee.news.koin.modules

import com.vsee.news.data.repository.features.news.FeedRepositoryImpl
import com.vsee.news.domain.features.news.repository.FeedRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { FeedRepositoryImpl(get(), get()) as FeedRepository }
}