package com.dharmraj.meeshotest.network.repository

import com.dharmraj.meeshotest.model.GitPullRequest
import com.dharmraj.meeshotest.network.source.Service
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PullRequestRepositoryImpl @Inject constructor(private val service: Service) : PullRequestRepository{

    @Volatile private var gitPullRequests : List<GitPullRequest>? = null

    override fun getPreFetchedPullRequests(): List<GitPullRequest>? = gitPullRequests

    override fun getPullRequests(githubOwnerName: String, githubRepoName: String): Observable<List<GitPullRequest>> =
            service.getPullRequests(githubOwnerName,githubRepoName).doOnNext {
                gitPullRequests = it
            }

}