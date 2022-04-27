package com.vsee.news.presentation.features.news.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.vsee.news.R
import com.vsee.news.base.BaseFragment
import com.vsee.news.databinding.FragmentFeedNewsBinding
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.news.adapter.FeedAdapter
import com.vsee.news.presentation.features.news.listener.FeedItemClickListener
import com.vsee.news.presentation.features.news.viewmodel.FeedViewModel
import com.vsee.news.utils.common.NavigationUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedNewsFragment : BaseFragment(), FeedItemClickListener {

    private var _binding: FragmentFeedNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FeedViewModel by viewModel()
    private var feedAdapter: FeedAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_feed_news, container, false)
        _binding?.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        handleListener()
        handleCallBack()
    }

    private fun initView() {
        feedAdapter = FeedAdapter(this)
        with(binding.rvFeedNews){
            itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this.context)
            adapter = feedAdapter
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initData(){
        viewModel.isShowProgress.value = true
        viewModel.onGetFeedData()
    }

    private fun handleListener() {
        binding.srFeedNews.setOnRefreshListener {
            viewModel.onGetFeedData()
        }
    }

    private fun handleCallBack() {
        viewModel.listArticleItem.observe(viewLifecycleOwner) { articleData ->
            binding.srFeedNews.isRefreshing = false
            viewModel.isShowProgress.value = false
                feedAdapter?.let { adapter ->
                articleData.data?.let {
                    adapter.onUpdateData(it)
                }
            }
        }

        viewModel.errorText.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            android.util.Log.e("TEST_DATA","errorText= $it")
        }
        viewModel.isShowProgress.observe(viewLifecycleOwner) {
            binding.vProgress.visibility = if(it) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(args: Bundle?) = FeedNewsFragment().apply {
            arguments = args
        }
    }

    override fun onFeedItemClick(articleItem: ArticleItem) {
        activity?.let {
            NavigationUtils.navigateToFeedDetailActivity(it, articleItem)
        }

    }
}