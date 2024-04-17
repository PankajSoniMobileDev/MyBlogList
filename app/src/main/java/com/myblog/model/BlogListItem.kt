package com.myblog.model

sealed class BlogListItem(val id:String,val title:String,val userId:String,val body:String) {
    data class Item(val blogs: BlogList) : BlogListItem(blogs.id,blogs.title,blogs.userId,blogs.body)
}