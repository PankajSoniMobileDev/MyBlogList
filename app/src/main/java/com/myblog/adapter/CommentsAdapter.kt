package com.myblog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myblog.databinding.CommentBlogsItemBinding
import com.myblog.model.BlogCommentList

class CommentsAdapter(private var data:MutableList<BlogCommentList>):RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CommentBlogsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }

    inner class ViewHolder(binding: CommentBlogsItemBinding): RecyclerView.ViewHolder(binding.root){
        private var mBinding:CommentBlogsItemBinding
        init {
            mBinding = binding
        }

        fun bindData(blogCommentList: BlogCommentList) {
            mBinding.comment=blogCommentList
        }
    }

}