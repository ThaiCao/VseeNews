package com.vsee.news

import android.app.Application
import com.vsee.news.koin.components.applicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(applicationComponent)
        }
    }
}