package com.appetizer.exam.ituneslist.source

import android.os.Bundle
import com.appetizer.exam.core.data.ApiService
import com.appetizer.exam.entities.ITunesEntity
import io.reactivex.Flowable
import io.reactivex.Flowable.error
import io.reactivex.Flowable.just
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by: david on 2019-02-20
 * All rights reserved.
 */
class ITunesListApiService @Inject constructor(): ApiService<Bundle?, List<ITunesEntity>>() {
    /**
     * request call to get itunes data from the api.
     *
     * @param [params] - parameters to be included in the request
     */
    override fun load(params: Bundle?): Flowable<out List<ITunesEntity>> {
        val baseUrl = "https://itunes.apple.com/"

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

        val api = retrofit.create(ITunesService::class.java)

        return api.list("star", "au", "movie")
                .flatMap {
                    Timber.d("requesting itunes")
                    if (it.resultCount > 0) {
                        return@flatMap just(it.results)
                    }

                    return@flatMap error<List<ITunesEntity>>(Throwable("No results found"))
                }
    }
}