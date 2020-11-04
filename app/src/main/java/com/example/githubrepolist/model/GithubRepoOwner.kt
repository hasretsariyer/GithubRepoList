package com.example.githubrepolist.model

import com.google.gson.annotations.SerializedName

data class GithubRepoOwner(
    @SerializedName("avatar_url")
    val userAvatar: String,
    @SerializedName("login")
    val name: String
)