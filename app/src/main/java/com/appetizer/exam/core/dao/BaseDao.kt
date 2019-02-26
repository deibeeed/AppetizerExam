package com.appetizer.exam.core.dao

import io.reactivex.Flowable

/**
 * Created by: david on 18/01/2019
 * All rights reserved.
 */
interface BaseDao<T> {
    fun load(id: String): Flowable<T>

    fun load(): Flowable<T>

    fun store(data: T): Flowable<T>

    fun remove(data: T): Flowable<T>

    fun update(data: T): Flowable<T>
}