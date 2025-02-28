package com.example.ecomproject.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecomproject.Adapter.CategoryAdapter
import com.example.ecomproject.Model.CategoryModel
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityCategoryViewBinding
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoryViewBinding
    private lateinit var apiInterface: ApiInterface
    private var list: MutableList<CategoryModel> = mutableListOf()
    private lateinit var adapter: CategoryAdapter
    private var pos2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.tool)

        val pos = intent.getIntExtra("pos", -1)
        if (pos == -1) {
            Toast.makeText(this, "Invalid category position!", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        pos2 = pos + 1

        binding.recycler.layoutManager = GridLayoutManager(this, 2)

        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        fetchCategoryData()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = list.filter {
                    it.name?.contains(newText ?: "", ignoreCase = true) == true
                }
                adapter.updateList(filteredList)
                return true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.ordermenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.i1 -> {
                val sortedList = list.sortedBy {
                    it.price?.toDoubleOrNull() ?: 0.0
                }
                adapter.updateList(sortedList)
                return true
            }
            R.id.i2 -> {
                val sortedList = list.sortedByDescending {
                    it.price?.toDoubleOrNull() ?: 0.0
                }
                adapter.updateList(sortedList)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun fetchCategoryData() {
        val call = apiInterface.categoryviewdata(pos2)
        call.enqueue(object : Callback<List<CategoryModel>> {
            override fun onResponse(call: Call<List<CategoryModel>>, response: Response<List<CategoryModel>>) {
                list = response.body()?.toMutableList() ?: mutableListOf()
                if (list.isEmpty()) {
                    Toast.makeText(applicationContext, "No items found!", Toast.LENGTH_SHORT).show()
                }
                adapter = CategoryAdapter(applicationContext, list)
                binding.recycler.adapter = adapter
            }

            override fun onFailure(call: Call<List<CategoryModel>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
