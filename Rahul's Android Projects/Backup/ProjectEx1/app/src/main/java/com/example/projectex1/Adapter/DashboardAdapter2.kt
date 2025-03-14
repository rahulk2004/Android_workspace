package com.example.projectex1

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.classproject.clients.ApiClient
import com.example.classproject.interfaces.ApiInterface
import com.example.projectex1.Activity.CategoryViewActivity
import com.example.projectex1.Model.DashboardModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardAdapter2(var context: Context,var list: MutableList<DashboardModel>):RecyclerView.Adapter<MyView2>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView2
    {
        var  layoutInflater= LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.design_dashboard2,parent,false)
        return MyView2(view)

    }

    override fun getItemCount(): Int
    {
        return list.size
    }

    override fun onBindViewHolder(holder: MyView2 , position: Int)
    {
        holder.textView.setText(list.get(position).name)

    }
}
class MyView2(Itemview:View):RecyclerView.ViewHolder(Itemview)
{
    var textView: TextView = Itemview.findViewById(R.id.dashboard_txt)
}