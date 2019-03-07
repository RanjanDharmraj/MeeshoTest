package com.dharmraj.meeshotest.di

import com.dharmraj.meeshotest.ui.MainActivity
import com.dharmraj.meeshotest.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}