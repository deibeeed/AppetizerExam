package com.appetizer.exam.core

import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_root_navigation.*

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
open class BaseFragment: Fragment() {
    val coreComponent: CoreComponent
        get() =  (activity as BaseActivity).coreComponent

    val rootContainer: View
        get() = (activity as BaseActivity).rootContainer
}