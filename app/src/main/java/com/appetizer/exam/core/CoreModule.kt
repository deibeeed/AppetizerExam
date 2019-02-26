package com.appetizer.exam.core

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import com.appetizer.exam.core.AppConstants.Companion.APPLICATION_CONTEXT
import com.appetizer.exam.core.AppConstants.Companion.IO_SCHEDULER
import com.appetizer.exam.core.AppConstants.Companion.MAIN_THREAD_SCHEDULER
import com.appetizer.exam.core.AppConstants.Companion.SHARED_PREFERENCE_NAME
import com.appetizer.exam.core.database.AppDatabaseModule
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
@Module
class CoreModule constructor(private val applicationContext: Context) {
    @Provides
    @Named(APPLICATION_CONTEXT)
    fun applicationContext(): Context = applicationContext

    @Provides
    fun resources(): Resources = applicationContext.resources

    @Provides
    @Named(IO_SCHEDULER)
    fun ioScheduler() = Schedulers.io()

    @Provides
    @Named(MAIN_THREAD_SCHEDULER)
    fun mainThreadScheduler() = AndroidSchedulers.mainThread()

    @Provides
    fun sharedPreferences(): SharedPreferences {
        return applicationContext.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)
    }
}