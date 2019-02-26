package com.appetizer.exam.core

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
@SuppressLint("Registered")
open class BaseActivity: AppCompatActivity() {
    private lateinit var appetizerExam: AppetizerExam
    internal val coreComponent: CoreComponent
        get() = appetizerExam.coreComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appetizerExam = application as AppetizerExam
    }

//    internal fun initializeToolbar() {
//        val navController = findNavController(R.id.navHostFragment)
////        val appBarConfiguration = AppBarConfiguration(navGraph = navController.graph)
//        val appBarConfiguration = AppBarConfiguration(
//            topLevelDestinationIds = setOf(
//                R.id.homeFragment,
//                R.id.searchFragment,
//                R.id.connectionFragment,
//                R.id.profileFragment)
//        )
//
//        toolbar.setupWithNavController(navController, appBarConfiguration)
//
//        if (supportActionBar == null) {
//            setSupportActionBar(toolbar)
//        }
//    }
}