package com.appetizer.exam.core.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

/**
 * Created by: david on 28/01/2019
 * All rights reserved.
 */
abstract class BaseViewModel: ViewModel() {
    abstract val type: Int

    /**
     * let all that extends this ViewModel to
     * implement onCleared() function of the
     * ViewModel class.
     */
    abstract override fun onCleared()

    abstract fun initiateComponentInjection()
}