package com.myblog

import com.myblog.model.BlogListItem

interface OnBlogItemClick {

    fun onBlogClick(post:BlogListItem)
}