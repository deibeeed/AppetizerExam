package com.appetizer.exam.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.appetizer.exam.core.dao.ITunesDao
import com.appetizer.exam.entities.ITunesEntity

/**
 * Created by: david on 2019-02-23
 * All rights reserved.
 */
@Database(entities = [ITunesEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun itunesDao(): ITunesDao

    companion object {
        private const val DATABASE_NAME = "app-database"
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                        .databaseBuilder(
                                context,
                                AppDatabase::class.java,
                                DATABASE_NAME)
                        .enableMultiInstanceInvalidation()
                        .build()
            }

            return INSTANCE!!
        }
    }
}