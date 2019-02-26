package com.appetizer.exam.itunesdetail.repository

import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.entities.ITunesEntity

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
interface ITunesDetailRepository {
    val itunes: MutableLiveData<DataWrapper<ITunesEntity>>

    fun saveLastScreen(withData: ITunesEntity)

    fun load()

    fun saveLastView()
}