package com.dharmraj.meeshotest.network.repository

import com.dharmraj.meeshotest.model.response.GitPullRequest
import com.dharmraj.meeshotest.model.PullRequestItem
import io.reactivex.Observable

interface PullRequestRepository {
    fun getPullRequests(githubOwnerName: String, githubRepoName: String): Observable<List<GitPullRequest>>
    fun getPreFetchedPullRequests() : List<PullRequestItem>?
}