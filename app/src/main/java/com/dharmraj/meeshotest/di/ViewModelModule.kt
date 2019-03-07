package com.dharmraj.meeshotest.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindAppViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}
