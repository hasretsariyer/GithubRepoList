package com.example.githubrepolist.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.githubrepolist.R
import com.example.githubrepolist.model.GithubRepoModel
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.repo_detail_layout.*

class DetailFragment : Fragment() {
    private lateinit var repoModel: GithubRepoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repoModel = arguments?.getSerializable("repoModel") as GithubRepoModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.repo_detail_fragment, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = repoModel.name
        starTextView.text = "${starTextView.text} ${repoModel.starCount}"
        openIssuesTextView.text = "${openIssuesTextView.text} ${repoModel.openIssues}"
        ownerTextView.text = "${ownerTextView.text} ${repoModel.owner.name}"
        Picasso.with(activity).load(repoModel.owner.avatarUrl).into(avatarImgView)
    }
}