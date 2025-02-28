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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomproject.Adapter.AdminDashboardAdapter
import com.example.ecomproject.Model.AdminDashboardModel
import com.example.ecomproject.R
import com.example.ecomproject.databinding.ActivityAdminDashboardBinding

class AdminDashboardActivity : AppCompatActivity() {

    lateinit var list: MutableList<AdminDashboardModel>
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if(Build.VERSION.SDK_INT >= 21){

            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.black)

        }

        sharedPreferences = getSharedPreferences("admin_session",Context.MODE_PRIVATE)
        Toast.makeText(applicationContext, "Welcome"+sharedPreferences.getString("mob",""), Toast.LENGTH_SHORT).show()

        setSupportActionBar(binding.tool)

        list = ArrayList()

        list.add(AdminDashboardModel(R.drawable.baseline_add_box_24,"Add Category"))
        list.add(AdminDashboardModel(R.drawable.baseline_preview_24,"Add Product"))
        list.add(AdminDashboardModel(R.drawable.baseline_preview_24,"View Orders"))

        var adapter = AdminDashboardAdapter(applicationContext,list)
        binding.grid.adapter = adapter

        binding.grid.setOnItemClickListener { parent, view, position, id ->

            if (position == 0)
            {
                startActivity(Intent(applicationContext,AdminCategoryActivity::class.java))
            }

            if (position == 1)
            {
                startActivity(Intent(applicationContext,AddProductActivity::class.java))
            }

            if (position == 2)
            {
                startActivity(Intent(applicationContext,PaymentViewActivity::class.java))
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.admin_dashboard_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.logout ->
            {
                sharedPreferences.edit().clear().commit()
                startActivity(Intent(applicationContext,AdminLoginActivity::class.java))
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}