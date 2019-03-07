package com.dharmraj.meeshotest.network

import android.content.Context
import android.net.ConnectivityManager
import com.dharmraj.meeshotest.exceptions.GithubException
import com.dharmraj.meeshotest.exceptions.NoInternetException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import retrofit2.Call

abstract class ServiceAbstract(val context: Context) {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private fun isNetworkConnected(): Boolean {
        val info = connectivityManager.activeNetworkInfo
        return !(info == null || !info.isConnected)
    }

    fun <T> getObservableFromCall(call: Call<T>): Observable<T> =
        Observable.create {
            if (isNetworkConnected()) {
                try {
                    val response = call.execute()
                    if (response.isSuccessful) {
                        val t = response.body()!!
                        it.onNext(t)
                        it.onComplete()
                    } else {
                        val exception: Throwable = GithubException(response.message())
                        it.onErrorIfNotDisposed(exception)
                    }
                } catch (ex: Exception) {
                    it.onErrorIfNotDisposed(UnknownError())
                }
            } else {
                it.onErrorIfNotDisposed(NoInternetException())
            }
        }
}

fun <T> ObservableEmitter<T>.onErrorIfNotDisposed(error: Throwable) {
    if (!this.isDisposed) {
        this.onError(error)
    }
}