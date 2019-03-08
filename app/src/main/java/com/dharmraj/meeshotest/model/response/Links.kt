package com.dharmraj.meeshotest.model.response

data class Links(
    val comments: Comments,
    val commits: Commits,
    val html: Html,
    val issue: Issue,
    val review_comment: ReviewComment,
    val review_comments: ReviewComments,
    val self: Self,
    val statuses: Statuses
)

data class ReviewComment(
    val href: String
)

data class Self(
    val href: String
)

data class Html(
    val href: String
)

data class Commits(
    val href: String
)

data class Issue(
    val href: String
)

data class Comments(
    val href: String
)

data class Statuses(
    val href: String
)

data class ReviewComments(
    val href: String
)