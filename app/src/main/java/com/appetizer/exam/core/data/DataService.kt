package com.appetizer.exam.core.data

import io.reactivex.Flowable

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 */
interface DataService<in P, E> {
    fun load(params: P): Flowable<out E>

    fun store(entity: E): Flowable<out E>

    fun remove(entity: E): Flowable<out E>
}