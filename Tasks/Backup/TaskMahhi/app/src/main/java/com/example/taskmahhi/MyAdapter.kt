package com.example.taskmahhi

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(var context:Context , var list:MutableList<Model> ) :BaseAdapter()
{
    override fun getCount(): Int {

        return list.size

    }

    override fun getItem(position: Int): Any {

        return position


    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design,parent,false)

        var txt1:TextView = view.findViewById(R.id.itemname)
        var img:ImageView = view.findViewById(R.id.itemimage)
        var txt2:TextView =view.findViewById(R.id.itemprice)

        txt1.setText(list.get(position).name)
        img.setImageResource(list.get(position).Image)
        txt2.setText(list.get(position).price)

        return view


    }


}