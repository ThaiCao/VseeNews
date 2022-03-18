package com.vsee.news.koin.modules

import com.vsee.news.presentation.features.newdetail.viewmodel.FeedDetailViewModel
import com.vsee.news.presentation.features.news.viewmodel.FeedViewModel
import com.vsee.news.utils.AppDispatchers
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    single {
        AppDispatchers(Dispatchers.Main, Dispatchers.IO, Dispatchers.Default)
    }

    viewModel {
        FeedViewModel(get(), get())
    }

    viewModel {
        FeedDetailViewModel()
    }
}