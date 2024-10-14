package com.example.taskcustomlist

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var listView: ListView
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

        listView= findViewById(R.id.list)
        list = ArrayList()

        list.add(Model(R.drawable.breakfast, "BreakFast"))
        list.add(Model(R.drawable.dessert, "Dessert"))
        list.add(Model(R.drawable.lunch,"Lunch" ))
        list.add(Model(R.drawable.streetfood,"Samosa"))
        list.add(Model(R.drawable.fastfood,"Burger"))

        var adapter=MyAdapter(applicationContext,list)
        listView.adapter=adapter


    }
}