package com.myblog.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.paging.*
import androidx.paging.map
import com.myblog.model.BlogListItem
import com.myblog.db.BlogDB
import com.myblog.network.ApiService
import com.myblog.network.RetrofitHelper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BlogsViewModel(application: Application) : ViewModel() {

    var db: BlogDB = BlogDB.get(application.applicationContext)

    @OptIn(DelicateCoroutinesApi::class) fun getBlogsFromServer() {
        GlobalScope.launch(CoroutineExceptionHandler { _, throwable ->
            println("Error: $throwable")
        }) {
            if (db.blogDao().isEmpty()) {
                val blogsApi = RetrofitHelper.getInstance().create(ApiService::class.java)
                Log.d("blogs", blogsApi.getBlogs().body()?.size.toString())
                blogsApi.getBlogs().body()?.let { db.blogDao().insert(it) }
            }
        }
    }

    val allBlogs: Flow<PagingData<BlogListItem>> = Pager(config = PagingConfig(pageSize = 10, enablePlaceholders = true, maxSize = 200)) {
        db.blogDao().allBlogs()
    }.flow.map { pagingData ->
        pagingData.map { blogs -> BlogListItem.Item(blogs) }
    }

    @OptIn(DelicateCoroutinesApi::class) fun refreshData() {
        GlobalScope.launch(CoroutineExceptionHandler { _, throwable ->
            println("Error: $throwable")
        }) {
            db.blogDao().deleteTable()
            getBlogsFromServer()
        }
    }

}