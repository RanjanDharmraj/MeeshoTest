package com.dharmraj.meeshotest.ui

import android.arch.lifecycle.ViewModel
import com.dharmraj.meeshotest.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideService(mainService: MainApiService): MainContract.Service

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}