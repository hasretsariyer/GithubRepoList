package com.example.githubrepolist.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.R
import com.example.githubrepolist.api.ApiClient
import com.example.githubrepolist.api.RetrofitInterface
import com.example.githubrepolist.model.GithubRepoModel
import com.example.githubrepolist.view.adapter.GithubRepoAdapter

import kotlinx.android.synthetic.main.repo_list_layout.*

import rx.Observer
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RepoListFragment : Fragment() {
    private var subscription: Subscription? = null
    private var adapter: GithubRepoAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.repo_list_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setClickListener()
    }

    private fun setClickListener() {
        submitBtn.setOnClickListener {
            progressCircular.visibility = View.VISIBLE
            val service: RetrofitInterface =
                ApiClient.getApiClient(userNameEditText.text.toString()).create(
                    RetrofitInterface::class.java
                )
            subscription = service.getUserRepos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<GithubRepoModel>> {
                    override fun onCompleted() {
                        progressCircular.visibility = View.GONE
                        Log.d("@@TAG", "In onCompleted()")
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        Log.d("@@TAG", "In onError()")
                    }

                    override fun onNext(gitHubRepos: List<GithubRepoModel>) {
                        Log.d("@@TAG", "In onNext()")
                        adapter?.updateData(gitHubRepos)
                    }
                })
        }
    }

    private fun setupRecyclerView() {
        Log.i("@@TAG", "setupRecyclerView")
        if(adapter != null)
            return
        Log.i("@@TAG", "setupRecyclerView createAdapter")
        adapter = GithubRepoAdapter()
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.adapter = adapter
    }
}