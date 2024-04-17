package com.myblog.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyBlogs") data class BlogList(
    val userId: String,
    @PrimaryKey(autoGenerate = true)  val id: Int,
    val title: String,
    val body: String,
)