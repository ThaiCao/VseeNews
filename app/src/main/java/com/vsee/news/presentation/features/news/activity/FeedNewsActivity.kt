package com.vsee.news.presentation.features.news.activity

import android.os.Bundle
import com.vsee.news.R
import com.vsee.news.base.BaseActivity
import com.vsee.news.presentation.features.news.fragment.FeedNewsFragment
import com.vsee.news.utils.common.ActivityUtils

class FeedNewsActivity  : BaseActivity(){
    private var feedFragment : FeedNewsFragment? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        feedFragment = FeedNewsFragment.newInstance(intent.extras)
        ActivityUtils.addFragmentToActivityWithTag(
            supportFragmentManager,
            feedFragment!!, R.id.fragment_container, FeedNewsFragment.javaClass.name
        )
    }
}