package com.vsee.news.presentation.features.newdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.jakewharton.rxbinding4.view.clicks
import com.vsee.news.R
import com.vsee.news.base.BaseFragment
import com.vsee.news.base.addTo
import com.vsee.news.databinding.FragmentFeedNewsDetailBinding
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.newdetail.viewmodel.FeedDetailViewModel
import com.vsee.news.utils.common.Constant
import com.vsee.news.utils.common.DrawableUtils
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedDetailFragment  : BaseFragment(){

    private var _binding: FragmentFeedNewsDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeedDetailViewModel by viewModel()
    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_feed_news_detail, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        handleClick()
        initListener()
    }

    private fun getData(){
        arguments?.let {
            if(it.containsKey(Constant.ARTICLE_ITEM)){
                it.getParcelable<ArticleItem>(Constant.ARTICLE_ITEM)?.let { it1 ->
                    viewModel.onUpdateArticleItem(
                        it1
                    )
                }
            }
        }
    }

    private fun initListener() {
        viewModel.articleItem.observe(viewLifecycleOwner) { articleData ->
            binding.articleItem = articleData
        }

    }

    private fun handleClick() {
        binding.btnBack.clicks().throttleFirst(Constant.DELAY_TIME, TimeUnit.MILLISECONDS)
            .subscribe {
                activity?.onBackPressed()
            }.addTo(compositeDisposable)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        compositeDisposable.clear()
    }

    companion object {
        fun newInstance(args: Bundle?) = FeedDetailFragment().apply {
            arguments = args
        }
    }

}