package com.example.githubrepolist.model

import com.google.gson.annotations.SerializedName

data class GithubRepoModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("user_avatar")
    val userAvatar: String,
    @SerializedName("open_issues")
    val openIssues: String,
    @SerializedName("stargazers_count")
    val starCount: Int
)
