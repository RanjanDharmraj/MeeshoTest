package com.dharmraj.meeshotest.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val disposeOnClear = CompositeDisposable()

    val title = MutableLiveData<String>()

    override fun onCleared() {
        disposeOnClear.clear()
        super.onCleared()
    }

    fun Disposable.untilCleared() {
        disposeOnClear += this
    }
}