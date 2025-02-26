package com.example.projectbloodbank

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.projectbloodbank.databinding.ActivityDonorBinding

class DonorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDonorBinding
    lateinit var db:DatabseClass
    lateinit var list: MutableList<Donor>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       binding = ActivityDonorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        db = Room.databaseBuilder(applicationContext,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()
        list = ArrayList()

        var rm : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = rm

        list = db.DonorDao().dviewdata()

        var adapter = MyAdapter(applicationContext,list)
        binding.recycler.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { adapter.filterList(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { adapter.filterList(it) }
                return true
            }
        })


        binding.f1.setOnClickListener{

            startActivity(Intent(applicationContext,AddDonerActivity::class.java))
        }
    }

}