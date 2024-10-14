package com.example.taskfilemanager

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController

class imgAdapter(var context:Context,var list: MutableList<imgModel>):BaseAdapter(){

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position : Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.image_design,parent,false)

        var img:ImageView =view.findViewById(R.id.image)
        var txt1:TextView = view.findViewById(R.id.txt1)
        var txt2:TextView = view.findViewById(R.id.txt2)

        img.setImageResource(list.get(position).image)
        txt1.setText(list.get(position).name)
        txt2.setText(list.get(position).item)

        return view
    }
}