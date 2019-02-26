package com.appetizer.exam.ituneslist.repository

import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.entities.ITunesEntity

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
interface ITunesListRepository {
    /**
     * Main repository of the itunes data
     * whether it comes from db or network call,
     * all itunes data are accessed from here
     */
    val itunes: MutableLiveData<DataWrapper<List<ITunesEntity>>>

    val lastView: MutableLiveData<DataWrapper<String>>

    /**
     * loads all itunes data.
     * trigger to start database call and network call to
     * get data from the sources.
     */
    fun load()

    fun saveLastScreen()

    fun saveLastView()
}