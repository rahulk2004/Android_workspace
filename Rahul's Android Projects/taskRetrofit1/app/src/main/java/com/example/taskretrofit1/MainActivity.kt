package com.example.taskretrofit1

import android.content.Intent
import android.os.Bundle
import retrofit2.Call
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    lateinit var list: List<Model>
    lateinit var apiinterface: Apiinterface
    lateinit var f1: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycle)
        list = ArrayList()
        f1 = findViewById(R.id.f1)

        var manager:RecyclerView.LayoutManager =LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)

        var call : Call<List<Model>> = apiinterface.getdata()



        call.enqueue(object :Callback<List<Model>>{

            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>)
            {
                list = response.body() as MutableList<Model>

                var myAdapter = MyAdapter(applicationContext, list as MutableList<Model>)
                recyclerView.adapter=myAdapter

            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Toast.makeText(applicationContext,"No Internet", Toast.LENGTH_LONG).show()
            }

        })

        f1.setOnClickListener{

            var i = Intent(applicationContext,InsertActivity::class.java)
            startActivity(i)
        }

    }
}