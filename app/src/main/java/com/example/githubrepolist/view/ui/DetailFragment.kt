package com.example.githubrepolist.view.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.githubrepolist.R
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.repo_detail_layout.*

class DetailFragment : Fragment() {
    private var starCount: Int? = null
    private var repoName: String? = null
    private var openIssues: Int? = null
    private var owner: String? = null
    private var avatarUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        starCount = arguments?.getInt("starCount")
        openIssues = arguments?.getInt("openIssues")
        repoName = arguments?.getString("repoName")
        owner = arguments?.getString("owner")
        avatarUrl = arguments?.getString("avatarUrl")
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

        (activity as AppCompatActivity).supportActionBar?.title = repoName
        starTextView.text = starTextView.text.toString() + starCount
        openIssuesTextView.text = openIssuesTextView.text.toString() + openIssues
        ownerTextView.text = openIssuesTextView.text.toString() + owner
        Picasso.with(activity).load(avatarUrl).into(avatarImgView)
    }
}