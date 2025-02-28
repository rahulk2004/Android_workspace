package com.example.ecomproject.Activity

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.Adapter.PaymentViewAdapter
import com.example.ecomproject.Model.Payment
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityPaymentViewBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PaymentViewActivity : AppCompatActivity() {
    
    lateinit var list: MutableList<Payment>
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityPaymentViewBinding
    lateinit var apiInterface: ApiInterface
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityPaymentViewBinding.inflate(layoutInflater)
        val view = binding.root
        
        setContentView(view)
        
        if (Build.VERSION.SDK_INT >= 21)
        {
            val window = this.window
            
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            
            window.statusBarColor = this.resources.getColor(R.color.black)
        }
        
        sharedPreferences = getSharedPreferences("admin_session",Context.MODE_PRIVATE)

        Toast.makeText(applicationContext, "Welcome"+sharedPreferences.getString("mob",""), Toast.LENGTH_SHORT).show()
        list = ArrayList()

        setSupportActionBar(binding.tool)

        var layoutmanager : RecyclerView.LayoutManager = GridLayoutManager(applicationContext,2)
        binding.recycler.layoutManager = layoutmanager

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        var call :Call<List<Payment>> = apiInterface.paymentview()

        call.enqueue(object :Callback<List<Payment>>{
            override fun onResponse(call: Call<List<Payment>>, response: Response<List<Payment>>) {

                list = response.body() as MutableList<Payment>

                var adapter = PaymentViewAdapter(applicationContext,list)

                binding.recycler.adapter = adapter
            }

            override fun onFailure(call: Call<List<Payment>>, t: Throwable) {

                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })
        
    }
}