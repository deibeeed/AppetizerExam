package com.appetizer.exam.core

import android.app.Application
import com.appetizer.exam.BuildConfig
import com.appetizer.exam.core.database.AppDatabaseComponent
import com.appetizer.exam.core.database.AppDatabaseModule
import com.appetizer.exam.core.database.DaggerAppDatabaseComponent
import com.facebook.drawee.backends.pipeline.Fresco
import timber.log.Timber

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
class AppetizerExam : Application() {
    lateinit var coreComponent: CoreComponent

    override fun onCreate() {
        super.onCreate()
        initializeTimber()
        Fresco.initialize(this)

        coreComponent = DaggerCoreComponent
                .builder()
                .coreModule(CoreModule(this))
//                .appDatabaseComponent(provideAppDatabaseComponent())
                .appDatabaseModule(AppDatabaseModule(this))
                .build()
    }

    private fun initializeTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun provideAppDatabaseComponent(): AppDatabaseComponent {
        return DaggerAppDatabaseComponent
                .builder()
                .appDatabaseModule(AppDatabaseModule(this))
                .build()
    }
}