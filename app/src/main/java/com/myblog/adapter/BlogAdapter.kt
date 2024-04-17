package com.myblog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.myblog.model.BlogListItem
import com.myblog.databinding.RowBlogsItemBinding

class BlogAdapter : PagingDataAdapter<BlogListItem, BlogAdapter.ViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBlogsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    inner class ViewHolder(binding: RowBlogsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private var mBinding: RowBlogsItemBinding

        init {
            mBinding = binding
        }

        fun bindData(blogList: BlogListItem?) {
            mBinding.blog = blogList
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<BlogListItem>() {
            override fun areItemsTheSame(oldItem: BlogListItem, newItem: BlogListItem): Boolean {
                return if (oldItem is BlogListItem.Item && newItem is BlogListItem.Item) {
                    oldItem.blogs.mId == newItem.blogs.mId
                } else {
                    oldItem == newItem
                }
            }
            override fun areContentsTheSame(oldItem: BlogListItem, newItem: BlogListItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}