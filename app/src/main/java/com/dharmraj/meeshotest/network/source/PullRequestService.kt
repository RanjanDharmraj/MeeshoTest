package com.dharmraj.meeshotest.network.source

import android.app.Application
import com.dharmraj.meeshotest.model.response.GitPullRequest
import com.dharmraj.meeshotest.network.ApiService
import com.dharmraj.meeshotest.network.ServiceAbstract
import io.reactivex.Observable
import javax.inject.Inject

class PullRequestService @Inject constructor(context: Application, private val apiService: ApiService) : ServiceAbstract(context), Service {

    override fun getPullRequests(githubOwnerName: String, githubRepoName: String): Observable<List<GitPullRequest>> {
        return getObservableFromCall(apiService.getAllPullRequests(githubOwnerName, githubRepoName))
    }

}