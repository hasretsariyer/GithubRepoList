package com.example.githubrepolist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RepoDetailViewModel : ViewModel() {
    val repoName: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val avatarUrl: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val starCount: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val openIssues: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}