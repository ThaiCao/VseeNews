package com.vsee.news.koin.modules

import com.vsee.news.domain.features.news.usecase.FeedUseCaseManager
import com.vsee.news.domain.features.news.usecase.GetListFeedNewsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { FeedUseCaseManager(get()) }
    factory { GetListFeedNewsUseCase(get()) }
}
