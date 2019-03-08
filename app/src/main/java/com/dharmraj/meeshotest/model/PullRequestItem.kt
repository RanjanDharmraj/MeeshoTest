package com.dharmraj.meeshotest.model

data class PullRequestItem(
    val title: String?,
    val state: String?,
    val number: Int,
    val locked: Boolean,
    val private:Boolean
)