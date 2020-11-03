package com.example.githubrepolist.view.adapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepolist.R
import com.example.githubrepolist.model.GithubRepoModel

class GithubRepoAdapter(gitHubRepos: List<GithubRepoModel>) :
    RecyclerView.Adapter<GithubRepoAdapter.CustomViewHolder>() {
    private val dataList: List<GithubRepoModel> = gitHubRepos

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val repoNameTextView: TextView = itemView.findViewById(R.id.repoName)

        fun bindItems(repoModel: GithubRepoModel) {
            repoNameTextView.text = repoModel.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bindItems(dataList.get(position))
        holder.itemView.setOnClickListener{
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                Log.i("@@TAG", "clicked index: " + holder.adapterPosition)
            }
        }
    }
}
