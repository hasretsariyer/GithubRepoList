package com.example.githubrepolist.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.R
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
    private val GITHUB_BASE_URL = "https://api.github.com/users/hasretsariyer/"
    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service: RetrofitInterface = getRetrofitInstance().create(
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
                    val adapter = GithubRepoAdapter(gitHubRepos)
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
            })
    }

    private fun getRetrofitInstance(): Retrofit {
        val gson: Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        return Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }
}