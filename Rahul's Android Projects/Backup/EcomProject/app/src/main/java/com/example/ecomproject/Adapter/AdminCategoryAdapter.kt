package com.example.ecomproject.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.Activity.EditCategoryActivity
import com.example.ecomproject.Model.CategoryModel
import com.example.ecomproject.R
import com.example.ecomproject.interfaces.ApiInterface
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminCategoryAdapter(var context: Context, var list: MutableList<CategoryModel>, private val apiInterface: ApiInterface) : RecyclerView.Adapter<AdminCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminCategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_category_item, parent, false)
        return AdminCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminCategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val category = list[position]
        holder.textView.text = category.name
        Picasso.get().load(category.url).into(holder.imageView)

        holder.btnEdit.setOnClickListener {
            val intent = Intent(context, EditCategoryActivity::class.java)
            intent.putExtra("id", category.id)
            intent.putExtra("name", category.name)
            intent.putExtra("url", category.url)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        holder.btnDelete.setOnClickListener {
            val call = apiInterface.deleteCategory(category.id!!)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    list.removeAt(position)
                    notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {}
            })
        }
    }

    override fun getItemCount(): Int = list.size
}

class AdminCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.admin_category_txt)
    val imageView: ImageView = view.findViewById(R.id.admin_category_img)
    val btnEdit: Button = view.findViewById(R.id.btn_edit)
    val btnDelete: Button = view.findViewById(R.id.btn_delete)
}
