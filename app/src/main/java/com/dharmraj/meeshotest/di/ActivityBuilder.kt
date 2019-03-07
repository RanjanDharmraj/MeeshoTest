package com.dharmraj.meeshotest.di

import com.dharmraj.meeshotest.ui.main.MainActivity
import com.dharmraj.meeshotest.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

}