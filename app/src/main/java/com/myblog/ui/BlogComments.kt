package com.myblog.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myblog.R
import com.myblog.adapter.CommentsAdapter
import com.myblog.databinding.BlgCommentsBinding
import com.myblog.model.BlogCommentList
import java.lang.reflect.Type


class BlogComments : AppCompatActivity() {

    private lateinit var binding: BlgCommentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.blg_comments)

        init()
    }

    /**
     * method used to initialization
     */
    @SuppressLint("SetTextI18n") private fun init() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = "Comments"
        val blogListType: Type = object : TypeToken<ArrayList<BlogCommentList>>() {}.type
        val commentList: MutableList<BlogCommentList> = Gson().fromJson(intent.getStringExtra("data"), blogListType)

        binding.commentId.text = "Comments On The Post: "+commentList[0].postId
        binding.commentsList.layoutManager = LinearLayoutManager(this)
        binding.commentsList.itemAnimator = DefaultItemAnimator()
        binding.commentsList.adapter = CommentsAdapter(commentList)
    }

}