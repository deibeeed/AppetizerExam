package com.appetizer.exam.ituneslist

import com.appetizer.exam.core.CoreComponent
import com.appetizer.exam.ituneslist.viewmodel.ITunesListViewModel
import dagger.Component

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
@ITunesListScope
@Component(dependencies = [CoreComponent::class], modules = [ITunesListModule::class])
interface ITunesListComponent {
    fun inject(viewModel: ITunesListViewModel)
}