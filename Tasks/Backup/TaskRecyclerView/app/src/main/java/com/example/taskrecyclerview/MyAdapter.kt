package com.example.taskrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class MyAdapter(var context :Context , var list: MutableList<Model>):RecyclerView.Adapter<Myview>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {
        var layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.card_design,parent,false)
        return Myview(view)
    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: Myview, position: Int) {
        holder.image.setImageResource(list.get(position).Image)
        holder.text.setText(list.get(position).name)
    }
}

class Myview(itemview:View):RecyclerView.ViewHolder(itemview)
{

    var image:ImageView
    var text :TextView

    init {
        image = itemview.findViewById(R.id.img)
        text = itemview.findViewById(R.id.txt)
    }

}