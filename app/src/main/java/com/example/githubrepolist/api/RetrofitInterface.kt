package com.example.githubrepolist.api

import com.example.githubrepolist.model.GithubRepoModel
import retrofit2.http.GET
import rx.Observable

interface RetrofitInterface {
    @GET("repos")
    fun getUserRepos(): Observable<List<GithubRepoModel>>
}