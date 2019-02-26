package com.appetizer.exam.core

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.appetizer.exam.core.AppConstants.Companion.APPLICATION_CONTEXT
import com.appetizer.exam.core.AppConstants.Companion.IO_SCHEDULER
import com.appetizer.exam.core.AppConstants.Companion.MAIN_THREAD_SCHEDULER
import com.appetizer.exam.core.dao.ITunesDao
import com.appetizer.exam.core.database.AppDatabase
import com.appetizer.exam.core.database.AppDatabaseComponent
import com.appetizer.exam.core.database.AppDatabaseModule
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
@Singleton
@Component(/*dependencies = [AppDatabaseComponent::class],*/ modules = [CoreModule::class, AppDatabaseModule::class])
interface CoreComponent {

    @Named(APPLICATION_CONTEXT)
    fun applicationContext(): Context

    fun resources(): Resources

    @Named(IO_SCHEDULER)
    fun ioScheduler(): Scheduler

    @Named(MAIN_THREAD_SCHEDULER)
    fun mainThreadScheduler(): Scheduler

    fun appDatabase(): AppDatabase

    fun itunesDao(): ITunesDao

    fun sharedPreferences(): SharedPreferences
}