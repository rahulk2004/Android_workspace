package com.example.taskcustomlist

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(var context: Context,var list: MutableList<Model>):BaseAdapter()

{
    override fun getCount(): Int {
       return list.size
    }

    override fun getItem(position: Int): Any {
       return position
    }

    override fun getItemId(position : Int): Long {

        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {

        var layout= LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design,parent,false)

        var img:ImageView = view.findViewById(R.id.img)
        var txt:TextView =view.findViewById(R.id.txt)


        img.setImageResource(list.get(position).image)
        txt.setText(list.get(position).txt)

        return view




    }


}