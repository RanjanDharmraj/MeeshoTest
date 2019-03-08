package com.dharmraj.meeshotest.ui.show_open_repo.adapter

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import com.dharmraj.meeshotest.model.PullRequestItem
import com.dharmraj.meeshotest.ui.show_open_repo.ShowOpenRepoActivity
import javax.inject.Inject

class PullRequestViewModel @Inject constructor(val appContext: Application): ViewModel() {

    companion object {
        fun create(
            context: ShowOpenRepoActivity,
            viewModelFactory: ViewModelProvider.Factory
        ): PullRequestViewModel {
            return ViewModelProviders.of(context, viewModelFactory).get(PullRequestViewModel::class.java)
        }
    }

    val title = MutableLiveData<String>()
    val pullRequestNumber = MutableLiveData<String>()

    fun loadData(item: PullRequestItem) {
        title.value = item.title
        pullRequestNumber.value = "# ${item.number}"
    }

}