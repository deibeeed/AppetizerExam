package com.appetizer.exam.ituneslist

import com.appetizer.exam.ituneslist.repository.ITunesListRepository
import com.appetizer.exam.ituneslist.repository.ITunesListRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
@Module
class ITunesListModule {
    @Provides
    fun provideITunesRepository(repositoryImpl: ITunesListRepositoryImpl): ITunesListRepository = repositoryImpl
}