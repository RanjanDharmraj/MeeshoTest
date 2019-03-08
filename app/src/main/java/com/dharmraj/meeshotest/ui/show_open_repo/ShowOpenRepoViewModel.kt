package com.dharmraj.meeshotest.ui.show_open_repo

import android.app.Application
import com.dharmraj.meeshotest.model.PullRequestItem
import com.dharmraj.meeshotest.network.repository.PullRequestRepository
import com.dharmraj.meeshotest.ui.base.BaseViewModel
import com.dharmraj.meeshotest.utils.SingleLiveEvent
import javax.inject.Inject

class ShowOpenRepoViewModel @Inject constructor(
    private val context: Application,
    private val repository: PullRequestRepository
) : BaseViewModel(context) {

    val pullRequestLists = SingleLiveEvent<List<PullRequestItem>>()

    init {
        title.value = "Open Pull Requests"
    }

    fun fetchPullRequestData() {
        pullRequestLists.value = repository.getPreFetchedPullRequests()
    }

}