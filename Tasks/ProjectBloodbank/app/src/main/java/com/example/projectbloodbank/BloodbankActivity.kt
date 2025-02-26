package com.example.projectbloodbank

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projectbloodbank.databinding.ActivityBloodbankBinding

class BloodbankActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBloodbankBinding
    lateinit var db: DatabseClass
    lateinit var list: MutableList<BloodBank>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBloodbankBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(applicationContext, DatabseClass::class.java, "myDatabase").allowMainThreadQueries().build()
        list = ArrayList()

        var rm: RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager=rm

        list = db.BloodbankDao().bbviewdata()

        var adapter = BloodbankAdapter(applicationContext,list)
        binding.recycler.adapter=adapter

        binding.f1.setOnClickListener {

            startActivity(Intent(applicationContext,AddBloodbankActivity::class.java))
        }


    }

}