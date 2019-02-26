package com.appetizer.exam.itunesdetail

import com.appetizer.exam.core.CoreComponent
import com.appetizer.exam.itunesdetail.viewmodel.ITunesDetailViewModel
import dagger.Component

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
@ITunesDetailScope
@Component(dependencies = [CoreComponent::class], modules = [ITunesDetailModule::class])
interface ITunesDetailComponent {
    fun inject(viewModel: ITunesDetailViewModel)
}