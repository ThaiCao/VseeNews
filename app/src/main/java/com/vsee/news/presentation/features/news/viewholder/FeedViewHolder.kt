package com.vsee.news.presentation.features.news.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vsee.news.R
import com.vsee.news.databinding.ItemFeedNewsBinding
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.news.listener.FeedItemClickListener

class FeedViewHolder (val binding: ItemFeedNewsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(articleItem: ArticleItem, listener : FeedItemClickListener){
        binding.cvRoot.setOnClickListener {
            listener.onFeedItemClick(articleItem)
        }
        binding.articleItem = articleItem
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): FeedViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding: ItemFeedNewsBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.item_feed_news,
                    parent, false)
            return FeedViewHolder(binding)
        }
    }
}