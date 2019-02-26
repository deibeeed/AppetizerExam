package com.appetizer.exam.ituneslist.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appetizer.exam.core.AppetizerExam
import com.appetizer.exam.core.CoreComponent

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
class ITunesViewModelFactory(private val coreComponent: CoreComponent): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ITunesListViewModel::class.java)) {
            return ITunesListViewModel(coreComponent = coreComponent) as T
        }

        throw IllegalArgumentException("Wrong ViewModel class")
    }

}