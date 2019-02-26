package com.appetizer.exam.itunesdetail.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.AppConstants
import com.appetizer.exam.core.AppConstants.Companion.LAST_SCREEN
import com.appetizer.exam.core.AppConstants.Companion.LAST_TRACK_ID
import com.appetizer.exam.core.AppConstants.Companion.LAST_VIEW_TIME
import com.appetizer.exam.core.AppConstants.Companion.SCREEN_ITEM_DETAIL
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.core.dao.ITunesDao
import com.appetizer.exam.entities.ITunesEntity
import io.reactivex.Scheduler
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
class ITunesDetailRepositoryImpl @Inject constructor(): ITunesDetailRepository {

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var itunesDao: ITunesDao

    @Inject
    @field:Named(AppConstants.IO_SCHEDULER)
    lateinit var ioScheduler: Scheduler

    @Inject
    @field:Named(AppConstants.MAIN_THREAD_SCHEDULER)
    lateinit var mainThreadScheduler: Scheduler

    override val itunes: MutableLiveData<DataWrapper<ITunesEntity>> = MutableLiveData()

    override fun saveLastScreen(withData: ITunesEntity) {
        preferences.edit { putString(LAST_SCREEN, SCREEN_ITEM_DETAIL) }
        preferences.edit { putLong(LAST_TRACK_ID, withData.trackId) }
    }

    override fun saveLastView() {
        preferences.edit{ putLong(LAST_VIEW_TIME, System.currentTimeMillis()) }
    }

    override fun load() {
        val lastScreen = preferences.getString(LAST_SCREEN, "") as String

        if (lastScreen == SCREEN_ITEM_DETAIL) {
            loadFromDb(preferences.getLong(LAST_TRACK_ID, 0L))
        }
    }

    private fun loadFromDb(trackId: Long) {
        val disposable = itunesDao
                .load(trackId = trackId)
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({
                    if (itunes.value == null) {
                        val wrapper = DataWrapper<ITunesEntity>()
                        itunes.value = wrapper
                    }

                    val wrapper = itunes.value!!
                    wrapper.data = it

                    itunes.value = wrapper

                },  {
                    Timber.e("something went wrong while getting data from db")
                    it.printStackTrace()

                    if (itunes.value == null) {
                        val wrapper = DataWrapper<ITunesEntity>()
                        itunes.value = wrapper
                    }

                    val wrapper = itunes.value!!
                    wrapper.error = it

                    itunes.value = wrapper
                })
    }
}