package com.myblog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyBlogs") data class BlogList(
    @PrimaryKey(autoGenerate = true) val mId: Int,
    val userId: String,
    val id: String,
    val title: String,
    val body: String,
)