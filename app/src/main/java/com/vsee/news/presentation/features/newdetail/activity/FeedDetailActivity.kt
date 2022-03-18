package com.vsee.news.presentation.features.newdetail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.vsee.news.R
import com.vsee.news.base.BaseActivity
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.newdetail.fragment.FeedDetailFragment
import com.vsee.news.utils.common.ActivityUtils
import com.vsee.news.utils.common.Constant.ARTICLE_ITEM

class FeedDetailActivity : BaseActivity(){
    private var feedDetailFragment : FeedDetailFragment? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        feedDetailFragment = FeedDetailFragment.newInstance(intent.extras)
        ActivityUtils.addFragmentToActivityWithTag(
            supportFragmentManager,
            feedDetailFragment!!, R.id.fragment_container, FeedDetailFragment.javaClass.name
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(from: Context, articleItem: ArticleItem): Intent = Intent(from, FeedDetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putExtra(ARTICLE_ITEM,articleItem)
            })
        }
    }
}