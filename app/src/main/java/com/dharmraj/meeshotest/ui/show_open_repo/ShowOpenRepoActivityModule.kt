package com.dharmraj.meeshotest.ui.show_open_repo

import android.arch.lifecycle.ViewModel
import com.dharmraj.meeshotest.di.ViewModelKey
import com.dharmraj.meeshotest.ui.show_open_repo.adapter.PullRequestViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ShowOpenRepoActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    abstract fun bindPullRequstViewModel(pullRequestViewModel: PullRequestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ShowOpenRepoViewModel::class)
    abstract fun bindShowOpenRepoViewModel(openRepoViewModel: ShowOpenRepoViewModel): ViewModel
}