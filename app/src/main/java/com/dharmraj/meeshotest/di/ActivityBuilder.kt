package com.dharmraj.meeshotest.di

import com.dharmraj.meeshotest.ui.main.MainActivity
import com.dharmraj.meeshotest.ui.main.MainActivityModule
import com.dharmraj.meeshotest.ui.show_open_repo.ShowOpenRepoActivity
import com.dharmraj.meeshotest.ui.show_open_repo.ShowOpenRepoActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ShowOpenRepoActivityModule::class])
    abstract fun showOpenRepoActivity(): ShowOpenRepoActivity

}