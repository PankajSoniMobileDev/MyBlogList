package com.myblog.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BlogViewModelFactory(private val app:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BlogsViewModel(app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}