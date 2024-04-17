package com.myblog.network

import com.myblog.model.BlogCommentList
import com.myblog.model.BlogList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {
    @GET("posts") suspend fun getBlogs(): Response<MutableList<BlogList>>

    @GET("posts/{id}/comments") suspend fun getBlogComments(@Path("id") postId: Int): Response<MutableList<BlogCommentList>>
}