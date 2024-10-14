package com.example.taskrecyclerview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycler)
        list = ArrayList()

        var manager:RecyclerView.LayoutManager = GridLayoutManager(this,2)

        recyclerView.layoutManager = manager


//        list.add(Model(R.drawable.p2img,"two"))
//        list.add(Model(R.drawable.p4img,"three"))

        for (i in 1..2000){
            list.add(Model(R.drawable.p1img,"one"))
        }

        var adapter = MyAdapter(applicationContext,list)
        recyclerView.adapter = adapter
    }
}