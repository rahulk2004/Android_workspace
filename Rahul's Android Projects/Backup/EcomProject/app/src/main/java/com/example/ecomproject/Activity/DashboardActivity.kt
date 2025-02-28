package com.example.ecomproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.interfaces.ApiInterface
import com.example.ecomproject.Adapter.DashboardAdapter
import com.example.ecomproject.Activity.LoginActivity
import com.example.ecomproject.Model.DashboardModel
import com.example.ecomproject.R
import com.example.ecomproject.databinding.ActivityDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityDashboardBinding
    lateinit var apiInterface: ApiInterface
    lateinit var list: MutableList<DashboardModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (Build.VERSION.SDK_INT >= 21 )
        {
            var window = this.window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            window.statusBarColor = this.resources.getColor(R.color.black)
        }

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        sharedPreferences = getSharedPreferences("user_session",Context.MODE_PRIVATE)

        Toast.makeText(applicationContext, "Welcome"+sharedPreferences.getString("mob",""), Toast.LENGTH_SHORT).show()

        var layoutmanager:RecyclerView.LayoutManager = GridLayoutManager(applicationContext,2)

        binding.recycler.layoutManager = layoutmanager

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        list = ArrayList()

        var call: Call<List<DashboardModel>> = apiInterface.dashboard_viewdata()


        call.enqueue(object :Callback<List<DashboardModel>>
        {
            override fun onResponse(
                call: retrofit2.Call<List<DashboardModel>>,
                response: Response<List<DashboardModel>>
            ) {
                list = response.body() as MutableList<DashboardModel>

                var adapter = DashboardAdapter(applicationContext,list)
                binding.recycler.adapter = adapter
            }

            override fun onFailure(call: retrofit2.Call<List<DashboardModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.wishlist->
            {
                startActivity(Intent(applicationContext,WishlistActivity::class.java))
            }
            R.id.cart->
            {
                startActivity(Intent(applicationContext,CartActivity::class.java))
            }
            R.id.logout->
            {
                sharedPreferences.edit().clear().commit()
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}