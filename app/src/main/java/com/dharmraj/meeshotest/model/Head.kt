package com.dharmraj.meeshotest.model

data class Head(

    val label: String,
    val ref: String,
    val repo: Repo,
    val sha: String,
    val user: User
)
