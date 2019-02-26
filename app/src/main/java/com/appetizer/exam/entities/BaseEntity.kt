package com.appetizer.exam.entities

/**
 * Created by: david on 18/01/2019
 * All rights reserved.
 */
abstract class BaseEntity {
    abstract val id: String
    var created: Long = System.currentTimeMillis()
    var updated: Long = System.currentTimeMillis()
    var deleted: Long = -1L
}