package com.example.ecomproject.Activity

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityWishlistAddBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Callback
import retrofit2.Response

class WishlistAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWishlistAddBinding
    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWishlistAddBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        var i = intent
        var g_name = i.getStringExtra("gift_name")
        var g_desc = i.getStringExtra("gift_desc")
        var g_price = i.getStringExtra("gift_price")
        var g_image = i.getStringExtra("gift_image")
        var mob = i.getStringExtra("mobile")

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        var call: retrofit2.Call<Void> = apiInterface.wishadddetail(g_name,g_desc,g_price,g_image,mob)

        call.enqueue(object :Callback<Void>{
            override fun onResponse(call: retrofit2.Call<Void>, response: Response<Void>) {
                startActivity(Intent(applicationContext,WishlistActivity::class.java))
            }

            override fun onFailure(call: retrofit2.Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })

    }
}