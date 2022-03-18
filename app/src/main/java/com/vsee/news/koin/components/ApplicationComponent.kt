package com.vsee.news.koin.components

import com.vsee.news.koin.modules.*

val applicationComponent = listOf(
    domainModule, localModule, remoteModule, repositoryModule,
    retrofitModule, viewModelModule
)