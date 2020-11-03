package com.example.githubrepolist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.R
import com.example.githubrepolist.api.ApiClient
import com.example.githubrepolist.api.RetrofitInterface
import com.example.githubrepolist.model.GithubRepoModel
import com.example.githubrepolist.view.adapter.GithubRepoAdapter
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private var subscription: Subscription? = null
    private var adapter: GithubRepoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAdapter()
    }

    override fun onPause() {
        subscription!!.unsubscribe()
        super.onPause()
    }

    private fun setAdapter() {
        adapter = GithubRepoAdapter(emptyList())
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }

    fun fetchAllRepos(view: View) {
        val service: RetrofitInterface =
            ApiClient.getApiClient(userNameTextView.text.toString()).create(
                RetrofitInterface::class.java
            )

        subscription = service.getUserRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<GithubRepoModel>> {
                override fun onCompleted() {
                    Log.d("@@TAG", "In onCompleted()")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.d("@@TAG", "In onError()")
                }

                override fun onNext(gitHubRepos: List<GithubRepoModel>) {
                    Log.d("@@TAG", "In onNext()")
                    adapter!!.updateData(gitHubRepos)
                }
            })
    }
}