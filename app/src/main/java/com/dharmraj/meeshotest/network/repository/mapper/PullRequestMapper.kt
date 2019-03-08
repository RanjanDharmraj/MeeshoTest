package com.dharmraj.meeshotest.network.repository.mapper

import com.dharmraj.meeshotest.model.response.GitPullRequest
import com.dharmraj.meeshotest.model.PullRequestItem

class PullRequestMapper : Mapper<GitPullRequest, PullRequestItem>() {

    override fun reverseMap(value: PullRequestItem): GitPullRequest {
        throw UnsupportedOperationException()
    }

    override fun map(value: GitPullRequest): PullRequestItem {
        return PullRequestItem(
            title = value.title,
            state = value.state,
            number = value.number,
            locked = value.locked,
            private = value.base.repo.private
        )
    }


}