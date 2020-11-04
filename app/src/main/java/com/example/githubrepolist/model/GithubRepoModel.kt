package com.example.githubrepolist.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class GithubRepoModel (
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: GithubRepoOwner,
    @SerializedName("open_issues")
    val openIssues: String,
    @SerializedName("stargazers_count")
    val starCount: Int
): Serializable
