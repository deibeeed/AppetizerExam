package com.appetizer.exam.core.data

import io.reactivex.Flowable

/**
 * Created by: david on 10/01/2019
 * All rights reserved.
 *
 * An Api Service doesn't need to store information from the request.
 * and it doesn't need to remove a value.
 *
 * Only Implement this abstract class/interface when you should request
 * something from the server thru an API.
 *
 * An Entity provided in an ApiService can be a list of entities (e.g List<E>)
 * and that's okay
 */
abstract class ApiService<P, E>: DataService<P, E> {
    override fun store(entity: E): Flowable<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(entity: E): Flowable<E> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}