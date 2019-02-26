package com.appetizer.exam.itunesdetail

import com.appetizer.exam.itunesdetail.repository.ITunesDetailRepository
import com.appetizer.exam.itunesdetail.repository.ITunesDetailRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
@Module
class ITunesDetailModule {

    @Provides
    fun provideITunesItemRepository(repositoryImpl: ITunesDetailRepositoryImpl): ITunesDetailRepository = repositoryImpl
}