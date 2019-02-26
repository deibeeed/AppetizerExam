package com.appetizer.exam.core.database

import com.appetizer.exam.core.dao.ITunesDao
import dagger.Component
import javax.inject.Singleton

/**
 * Created by: david on 2019-02-23
 * All rights reserved.
 */
@Component(modules = [AppDatabaseModule::class])
interface AppDatabaseComponent {
    fun appDatabase(): AppDatabase

    fun itunesDao(): ITunesDao
}