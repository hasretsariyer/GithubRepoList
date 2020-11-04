package com.example.githubrepolist.view.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.R
import com.example.githubrepolist.api.ApiClient
import com.example.githubrepolist.api.RetrofitInterface
import com.example.githubrepolist.model.GithubRepoModel
import com.example.githubrepolist.view.adapter.GithubRepoAdapter
import com.example.githubrepolist.viewmodel.RepoDetailViewModel
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

    private lateinit var viewModel: RepoDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        NavigationUI.setupActionBarWithNavController(this, findNavController(R.id.main_nav_fragment))
//        setupRecyclerView()
    }

    override fun onSupportNavigateUp() = findNavController(R.id.main_nav_fragment).navigateUp()

//    override fun onPause() {
//        if(!subscription!!.isUnsubscribed) {
//            subscription!!.unsubscribe()
//        }
//        super.onPause()
//    }
//
//
//    fun fetchAllRepos(view: View) {
//        val service: RetrofitInterface =
//            ApiClient.getApiClient(userNameEditText.text.toString()).create(
//                RetrofitInterface::class.java
//            )
//
//        progressCircular.visibility = View.VISIBLE
//        hideKeyboard()
//
//        subscription = service.getUserRepos()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<List<GithubRepoModel>> {
//                override fun onCompleted() {
//                    progressCircular.visibility = View.GONE
//                    Log.d("@@TAG", "In onCompleted()")
//                }
//
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    Log.d("@@TAG", "In onError()")
//                }
//
//                override fun onNext(gitHubRepos: List<GithubRepoModel>) {
//                    Log.d("@@TAG", "In onNext()")
//                    adapter!!.updateData(gitHubRepos)
//                }
//            })
//    }
//
//    private fun hideKeyboard() {
//        val inputManager: InputMethodManager =getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.SHOW_FORCED)
//    }
}