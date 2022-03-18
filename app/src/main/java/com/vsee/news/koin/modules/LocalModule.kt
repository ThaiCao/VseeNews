package com.vsee.news.koin.modules

import com.vsee.news.data.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.buildDatabase(androidContext())}
    factory { (get() as AppDatabase).newsFeedDao() }

}