package com.dharmraj.meeshotest

import android.app.Activity
import android.app.Application
import com.dharmraj.meeshotest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MeeshoTestApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        initializeAppComponent()
    }

    fun initializeAppComponent() {
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}