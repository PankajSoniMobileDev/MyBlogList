package com.myblog.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myblog.model.BlogList

@Database(entities = [BlogList::class], version = 1) abstract class BlogDB() : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        private var instance: BlogDB? = null

        @Synchronized fun get(context: Context): BlogDB {
            if (instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, BlogDB::class.java, "BlogDatabase").fallbackToDestructiveMigration().build()
            }
            return instance!!
        }
    }
}