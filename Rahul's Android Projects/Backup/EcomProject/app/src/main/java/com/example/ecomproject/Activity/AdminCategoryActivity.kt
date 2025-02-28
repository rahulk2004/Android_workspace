package com.example.ecomproject.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecomproject.Adapter.AdminCategoryAdapter
import com.example.ecomproject.Model.CategoryModel
import com.example.ecomproject.Model.DashboardModel
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityAdminCategoryBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminCategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminCategoryBinding
    private lateinit var apiInterface: ApiInterface
    private var list: MutableList<CategoryModel> = mutableListOf()
    private lateinit var adapter: AdminCategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
//        fetchCategoryData()

        binding.btnAddCategory.setOnClickListener {
            startActivity(Intent(this, AddCategoryActivity::class.java))
        }
    }

//    private fun fetchCategoryData() {
//        val call: Call<List<CategoryModel>> = apiInterface.dashboard_viewdata()
//        call.enqueue(object : Callback<List<CategoryModel>> {
//            override fun onResponse(call: Call<List<CategoryModel>>, response: Response<List<CategoryModel>>) {
//                if (response.isSuccessful && response.body() != null) {
//                    list = response.body()!!.toMutableList()
//                    adapter = AdminCategoryAdapter(this@AdminCategoryActivity, list, apiInterface)
//                    binding.recycler.adapter = adapter
//                } else {
//                    Toast.makeText(this@AdminCategoryActivity, "Failed to load categories", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<CategoryModel>>, t: Throwable) {
//                Toast.makeText(this@AdminCategoryActivity, "No Internet Connection", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }


}
