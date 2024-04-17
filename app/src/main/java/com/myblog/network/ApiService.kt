package com.myblog.network

import com.myblog.model.BlogList
import retrofit2.Response
import retrofit2.http.GET




interface  ApiService {
    @GET("posts")
    suspend fun getBlogs() : Response<MutableList<BlogList>>
}