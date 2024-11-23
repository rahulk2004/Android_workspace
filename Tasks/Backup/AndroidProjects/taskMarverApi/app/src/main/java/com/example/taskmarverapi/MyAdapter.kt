package com.example.taskmarverapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

 class MyAdapter(var context: Context, var list: MutableList<Model>) : RecyclerView.Adapter<MyView>()
 {
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView
     {
         var layoutInflater = LayoutInflater.from(context)
         var view = layoutInflater.inflate(R.layout.design,parent,false)
         return MyView(view)
     }

     override fun getItemCount(): Int {
         return list.size
     }

     override fun onBindViewHolder(holder: MyView, position: Int) {
         holder.txt1.setText(list.get(position).name)
         holder.txt2.setText(list[position].realname)
         holder.txt3.setText(list.get(position).team)
         holder.txt4.setText(list.get(position).firstappearance)
         holder.txt5.setText(list.get(position).createdby)
         holder.txt6.setText(list.get(position).publisher)
         holder.txt7.setText(list.get(position).bio)
         Picasso.get().load(list.get(position).imageurl).into(holder.img1)
     }

 }
class MyView(itemview: View) : RecyclerView.ViewHolder(itemview)
{

    var txt1:TextView
    var txt2:TextView
    var txt3:TextView
    var txt4:TextView
    var txt5:TextView
    var txt6:TextView
    var txt7:TextView
    var img1:ImageView

    init {
        txt1 = itemview.findViewById(R.id.txt1)
        txt2 = itemview.findViewById(R.id.txt2)
        txt3 = itemview.findViewById(R.id.txt3)
        txt4 = itemview.findViewById(R.id.txt4)
        txt5 = itemview.findViewById(R.id.txt5)
        txt6 = itemview.findViewById(R.id.txt6)
        txt7 = itemview.findViewById(R.id.txt7)
        img1 = itemview.findViewById(R.id.img)

    }
}