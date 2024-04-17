package com.myblog.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myblog.model.BlogList

@Dao
interface BlogDao {

    @Query("SELECT * FROM MyBlogs ORDER BY id COLLATE NOCASE ASC")
    fun allBlogs(): PagingSource<Int, BlogList>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: MutableList<BlogList>)

    @Query("SELECT (SELECT COUNT(*) FROM MyBlogs) == 0")
    fun isEmpty(): Boolean

    @Query("DELETE FROM MyBlogs")
    fun deleteTable(): Int
}
