package com.dharmraj.meeshotest.network.source

import com.dharmraj.meeshotest.model.GitPullRequest
import io.reactivex.Observable

interface Service {
    fun getPullRequests(githubOwnerName: String, githubRepoName: String): Observable<List<GitPullRequest>>
}