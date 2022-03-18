package com.vsee.news.presentation.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vsee.news.domain.features.news.model.ArticleItem
import com.vsee.news.presentation.features.news.listener.FeedItemClickListener
import com.vsee.news.presentation.features.news.viewholder.FeedViewHolder

class FeedAdapter  internal constructor(
    private val mListener: FeedItemClickListener
) : RecyclerView.Adapter<FeedViewHolder>() {
    private var articleList: List<ArticleItem> = arrayListOf()

    fun onUpdateData(articleList: List<ArticleItem>){
        this.articleList = articleList
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder = FeedViewHolder.from(parent)

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) = holder.bind(articleList[position], mListener)

    override fun getItemCount(): Int = articleList.size
}