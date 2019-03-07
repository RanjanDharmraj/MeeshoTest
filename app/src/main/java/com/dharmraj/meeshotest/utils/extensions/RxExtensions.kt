package com.dharmraj.meeshotest.utils.extensions

import io.reactivex.Observable

fun <T> Observable<T>.applySchedulers(): Observable<T> {
    return this.compose(com.dharmraj.meeshotest.utils.applySchedulers())
}