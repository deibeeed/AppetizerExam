package com.appetizer.exam.ituneslist.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.AppConstants
import com.appetizer.exam.core.AppConstants.Companion.IO_SCHEDULER
import com.appetizer.exam.core.AppConstants.Companion.LAST_VIEW_TIME
import com.appetizer.exam.core.AppConstants.Companion.MAIN_THREAD_SCHEDULER
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.core.dao.ITunesDao
import com.appetizer.exam.entities.ITunesEntity
import com.appetizer.exam.ituneslist.source.ITunesListApiService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
class ITunesListRepositoryImpl @Inject constructor(): ITunesListRepository {
    private val disposables = CompositeDisposable()

    @Inject
    lateinit var preferences: SharedPreferences

    @Inject
    lateinit var itunesListApiService: ITunesListApiService

    @Inject
    @field:Named(IO_SCHEDULER)
    lateinit var ioScheduler: Scheduler

    @Inject
    @field:Named(MAIN_THREAD_SCHEDULER)
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var itunesDao: ITunesDao

    /**
     * data source of truth
     */
    override val itunes: MutableLiveData<DataWrapper<List<ITunesEntity>>> = MutableLiveData()

    override val lastView: MutableLiveData<DataWrapper<String>> = MutableLiveData()

    override fun saveLastScreen() {
        preferences.edit { putString(AppConstants.LAST_SCREEN, AppConstants.SCREEN_ITEMS) }
    }

    override fun saveLastView() {
        preferences.edit{ putLong(AppConstants.LAST_VIEW_TIME, System.currentTimeMillis()) }
    }

    /**
     * loads all data from db and api request
     */
    override fun load() {
        Timber.d("load repository")
//        val disposable = Flowable.concat(loadFromDb(), loadFromNetwork())
//                .subscribeOn(ioScheduler)
//                .observeOn(mainThreadScheduler)
//                .subscribe({
//                    itunes.value = it
//                }, {
//                    Timber.e("Something went wrong while getting itunes data")
//                    it.printStackTrace()
//                }, {
//                    Timber.i("getting data comlete")
//                })

        loadLastViewTime()
        loadFromDb()
        loadFromNetwork()
    }

    /**
     * loads data from db
     */
    private fun loadFromDb() {
        itunesDao
                .load()
                .subscribeOn(ioScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe({
                    Timber.d("taken values from db")
                    if (itunes.value == null) {
                        val wrapper = DataWrapper<List<ITunesEntity>>()
                        itunes.value = wrapper
                    }

                    val wrapper = itunes.value!!
                    wrapper.data = it

                    itunes.value = wrapper
                }, {
                    Timber.e("an error occurred while loading data from db")
                    it.printStackTrace()

                    if (itunes.value == null) {
                        val wrapper = DataWrapper<List<ITunesEntity>>()
                        itunes.value = wrapper
                    }

                    val wrapper = itunes.value!!
                    wrapper.error = it

                    itunes.value = wrapper
                })
                .addTo(disposables)
    }

    /**
     * loads data from network call
     */
    private fun loadFromNetwork() {
        itunesListApiService
                .load(null)
                .flatMap { entities ->
                    return@flatMap itunesDao
                            .insert(entities)
                            .toFlowable<Int>()
                            .map {
                                return@map entities
                            }
                }
                .subscribeOn(ioScheduler)
                .subscribe({ Timber.d("successfully getting items from api") },
                        {
                            Timber.e("Something went wrong while getting items from api")
                            it.printStackTrace()
                        }, { Timber.i("getting items from api complete") })
                .addTo(disposables)
    }

    private fun loadLastViewTime() {
        val lastView = preferences.getLong(LAST_VIEW_TIME, 0L)
        val formatter = SimpleDateFormat("E, MMMM dd, yyyy")

        if (lastView > 0L) {
            val data = formatter.format(Date(lastView))

            val wrapper = DataWrapper<String>()
            wrapper.data = data
            this.lastView.value = wrapper
        } else {
            val data = formatter.format(Date())

            val wrapper = DataWrapper<String>()
            wrapper.data = data
            this.lastView.value = wrapper
        }
    }
}