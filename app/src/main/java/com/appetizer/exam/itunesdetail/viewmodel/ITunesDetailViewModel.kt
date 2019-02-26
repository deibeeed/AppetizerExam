package com.appetizer.exam.itunesdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.appetizer.exam.core.CoreComponent
import com.appetizer.exam.core.DataWrapper
import com.appetizer.exam.core.view.BaseViewModel
import com.appetizer.exam.entities.ITunesEntity
import com.appetizer.exam.itunesdetail.DaggerITunesDetailComponent
import com.appetizer.exam.itunesdetail.ITunesDetailComponent
import com.appetizer.exam.itunesdetail.ITunesDetailModule
import com.appetizer.exam.itunesdetail.repository.ITunesDetailRepository
import javax.inject.Inject

class ITunesDetailViewModel(private val coreComponent: CoreComponent) : BaseViewModel() {
    private lateinit var itunesDetailComponent: ITunesDetailComponent

    @Inject
    lateinit var repository: ITunesDetailRepository

    var itunes = MutableLiveData<DataWrapper<ITunesEntity>>()
        get() = repository.itunes
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
    }

    override fun initiateComponentInjection() {
        itunesDetailComponent = DaggerITunesDetailComponent
                .builder()
                .coreComponent(coreComponent)
                .iTunesDetailModule(ITunesDetailModule())
                .build()
                .apply {
                    inject(this@ITunesDetailViewModel)
                }
    }

    fun saveLastScreen(withData: ITunesEntity) {
        repository.saveLastScreen(withData)
    }

    fun updateLastView() {
        repository.saveLastView()
    }
}
