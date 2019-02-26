package com.appetizer.exam.itunesdetail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.appetizer.exam.core.CoreComponent

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
class ITunesDetailViewModelFactory(private val coreComponent: CoreComponent): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ITunesDetailViewModel::class.java)) {
            return ITunesDetailViewModel(coreComponent = coreComponent) as T
        }

        throw IllegalArgumentException("Wrong ViewModel class")
    }

}