package com.dharmraj.meeshotest.model.response

data class Head(

    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: User
)
