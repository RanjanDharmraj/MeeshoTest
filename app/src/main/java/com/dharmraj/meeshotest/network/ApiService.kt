package com.dharmraj.meeshotest.network

import com.dharmraj.meeshotest.model.GitPullRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/repos/{owner}/{repo}/pulls")
    fun getAllPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
        ) : Call<List<GitPullRequest>>

}