package com.dharmraj.meeshotest.ui.main

import android.arch.lifecycle.ViewModel
import com.dharmraj.meeshotest.di.ViewModelKey
import com.dharmraj.meeshotest.network.repository.PullRequestRepository
import com.dharmraj.meeshotest.network.repository.PullRequestRepositoryImpl
import com.dharmraj.meeshotest.network.source.PullRequestService
import com.dharmraj.meeshotest.network.source.Service
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideRepository(repository: PullRequestRepositoryImpl): PullRequestRepository

    @Binds
    abstract fun provideService(mainService: PullRequestService): Service

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}