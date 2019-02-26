package com.appetizer.exam.ituneslist.viewmodel

import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.CoreComponent
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.core.view.BaseViewModel
import com.appetizer.exam.entities.ITunesEntity
import com.appetizer.exam.ituneslist.DaggerITunesListComponent
import com.appetizer.exam.ituneslist.ITunesListComponent
import com.appetizer.exam.ituneslist.ITunesListModule
import com.appetizer.exam.ituneslist.repository.ITunesListRepository
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class ITunesListViewModel(private val coreComponent: CoreComponent) : BaseViewModel() {
    @Inject
    lateinit var repository: ITunesListRepository

    private lateinit var component: ITunesListComponent

    private val disposables = CompositeDisposable()

    var itunes = MutableLiveData<DataWrapper<List<ITunesEntity>>>()
        get() = repository.itunes
        private set(value) {
            field = value
        }

    var lastView = MutableLiveData<DataWrapper<String>>()
        get() = repository.lastView
        private set(value) {
            field = value
        }


    init {
        initiateComponentInjection()
        repository.load()
    }

    override val type: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onCleared() {
        // clear disposables here if there are any or
        // any thing that has strong reference to variables
        // to avoid memory leaks
        disposables.dispose()
    }

    override fun initiateComponentInjection() {
        component = DaggerITunesListComponent
                .builder()
                .coreComponent(coreComponent)
                .iTunesListModule(ITunesListModule())
                .build()
                .apply {
                    inject(this@ITunesListViewModel)
                }
    }

    fun updateLastView() {
        repository.saveLastScreen()
        repository.saveLastView()
    }
}
