package com.appetizer.exam.core.database

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by: david on 2019-02-23
 * All rights reserved.
 */
@Module
class AppDatabaseModule(private val context: Context) {
    @Provides
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun itunesDao(database: AppDatabase) = database.itunesDao()
}