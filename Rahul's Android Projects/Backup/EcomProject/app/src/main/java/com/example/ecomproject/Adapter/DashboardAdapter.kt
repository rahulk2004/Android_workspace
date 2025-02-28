package com.example.ecomproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.Activity.CategoryViewActivity
import com.example.ecomproject.Model.DashboardModel
import com.example.ecomproject.R
import com.squareup.picasso.Picasso

class DashboardAdapter(var context: Context,var list: MutableList<DashboardModel>):RecyclerView.Adapter<MyView>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {

        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.design_dashboard,parent,false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
       holder.textView.setText(list.get(position).name)

        Picasso.get().load(list.get(position).url).into(holder.imageview2)

        holder.itemView.setOnClickListener {

            var i = Intent(context, CategoryViewActivity::class.java)
            i.putExtra("pos",position)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)

        }
    }
}

class MyView(Itemview:View):RecyclerView.ViewHolder(Itemview) {


    var textView: TextView = Itemview.findViewById(R.id.dashboard_txt)
    var imageview2: ImageView = Itemview.findViewById(R.id.dashboard_img)


}
