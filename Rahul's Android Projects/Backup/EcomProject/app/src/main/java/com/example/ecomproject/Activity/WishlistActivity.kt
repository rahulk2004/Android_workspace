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
import com.example.ecomproject.Adapter.WishlistAdapter
import com.example.ecomproject.Model.WishlistModel
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityWishlistBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WishlistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistBinding
    private lateinit var mutableList: MutableList<WishlistModel>
    lateinit var apiInterface: ApiInterface
    lateinit var sharedPreferences: SharedPreferences
    lateinit var mAdapter: WishlistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        val view = binding.root

        WindowCompat.setDecorFitsSystemWindows(window,false)

        binding.mToolbar.title = "My Wishlist"
        setSupportActionBar(binding.mToolbar)
        binding.mToolbar.setNavigationOnClickListener{
            startActivity(Intent(applicationContext,DashboardActivity::class.java))
        }

        setContentView(view)

        mutableList = ArrayList()
        mAdapter = WishlistAdapter(applicationContext,mutableList)
        binding.recycler.layoutManager = LinearLayoutManager(this)

        sharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)

        val mob =sharedPreferences.getString("mob","")

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        val call = apiInterface.wishlistViewData(mob)

        call.enqueue(object : Callback<List<WishlistModel>>{
            override fun onResponse(
                call: Call<List<WishlistModel>>,
                response: Response<List<WishlistModel>>
            ) {
                mutableList = response.body() as MutableList<WishlistModel>
                Toast.makeText(applicationContext, ""+mutableList.size, Toast.LENGTH_SHORT).show()
                mAdapter = WishlistAdapter(applicationContext,mutableList)

                binding.recycler.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<WishlistModel>>, t: Throwable) {

                Toast.makeText(this@WishlistActivity, "Failed", Toast.LENGTH_SHORT).show()
            }


        })

    }
}