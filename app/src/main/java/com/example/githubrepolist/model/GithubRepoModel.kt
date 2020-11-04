package com.example.githubrepolist.model

import com.google.gson.annotations.SerializedName

data class GithubRepoModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: GithubRepoOwner,
    @SerializedName("open_issues")
    val openIssues: String,
    @SerializedName("stargazers_count")
    val starCount: Int
)
