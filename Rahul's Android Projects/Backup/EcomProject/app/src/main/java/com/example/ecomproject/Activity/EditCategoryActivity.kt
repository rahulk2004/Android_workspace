package com.example.ecomproject.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.interfaces.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditCategoryActivity : AppCompatActivity() {
    private lateinit var edtName: EditText
    private lateinit var btnUpdate: Button
    private lateinit var apiInterface: ApiInterface
    private var categoryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_category)

        edtName = findViewById(R.id.edt_name)
        btnUpdate = findViewById(R.id.btn_update)
        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        categoryId = intent.getIntExtra("id", -1)
        edtName.setText(intent.getStringExtra("name"))

        btnUpdate.setOnClickListener {
            updateCategory()
        }
    }

    private fun updateCategory() {
        val name = edtName.text.toString()
        val call = apiInterface.updateCategory(categoryId!!, name)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(applicationContext, "Category Updated", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {}
        })
    }
}
