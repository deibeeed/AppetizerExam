package com.appetizer.exam

import com.appetizer.exam.core.CoreComponent
import dagger.Component

/**
 * Created by: david on 2019-02-26
 * All rights reserved.
 */
@RootNavigationScope
@Component(dependencies = [CoreComponent::class])
interface RootNavigationComponent {
    fun inject(activity: RootNavigationActivity)
}