package com.example.githubrepolist.model

import com.google.gson.annotations.SerializedName

import java.io.Serializable

data class GithubRepoOwner(
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("login")
    val name: String
) : Serializable