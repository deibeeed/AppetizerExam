package com.appetizer.exam.core

import java.lang.Exception

/**
 * Created by: david on 2019-02-25
 * All rights reserved.
 */
class DataWrapper<T> {
    var data: T? = null
    var error: Throwable? = null
}