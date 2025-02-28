package com.example.module7coroutines

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val viewModel: PostViewModel by viewModels()
    private lateinit var adapter: PostAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)

        adapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.posts.observe(this) { posts ->
            adapter.submitList(posts)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.fetchPosts()
    }
}
