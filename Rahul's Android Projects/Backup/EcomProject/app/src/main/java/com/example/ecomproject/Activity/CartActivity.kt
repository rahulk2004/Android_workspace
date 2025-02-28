package com.example.ecomproject.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomproject.Adapter.CartAdapter
import com.example.ecomproject.Model.CartModel
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityCartBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var mutableList: MutableList<CartModel>
    lateinit var apiInterface: ApiInterface
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        val view = binding.root

        WindowCompat.setDecorFitsSystemWindows(window,false)

        binding.mToolbar.title = "My Cart"
        setSupportActionBar(binding.mToolbar)

        binding.mToolbar.setNavigationOnClickListener{

            startActivity(Intent(applicationContext,DashboardActivity::class.java))
        }

        setContentView(view)

        mutableList = ArrayList()
        mAdapter = CartAdapter(applicationContext,mutableList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)
        val mob = sharedPreferences.getString("mob","")

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
        val call = apiInterface.cartViewData(mob)

        call.enqueue(object : Callback<List<CartModel>>{
            override fun onResponse(
                call: Call<List<CartModel>>,
                response: Response<List<CartModel>>
            ) {
                mutableList = response.body() as MutableList<CartModel>

                mAdapter = CartAdapter(applicationContext,mutableList)

                binding.recyclerView.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<CartModel>>, t: Throwable) {

                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
            }

        })

    }
}