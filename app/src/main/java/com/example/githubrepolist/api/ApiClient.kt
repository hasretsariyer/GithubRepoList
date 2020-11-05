package com.example.githubrepolist.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

var githubBaseUrl = "https://api.github.com/users/"

class ApiClient {
    companion object {
        fun getApiClient(username: String): Retrofit {
            val gson: Gson =
                GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()

            return Retrofit.Builder().baseUrl("$githubBaseUrl$username/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
    }
}