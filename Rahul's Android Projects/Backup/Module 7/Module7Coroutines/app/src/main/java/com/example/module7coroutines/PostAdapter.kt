package com.example.module7coroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var posts: List<Post> = emptyList()

    fun submitList(list: List<Post>) {
        posts = list
        notifyDataSetChanged()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.postTitle)
        val body: TextView = itemView.findViewById(R.id.postBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.title.text = post.title
        holder.body.text = post.body
    }

    override fun getItemCount(): Int = posts.size
}