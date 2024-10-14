package com.example.tasknavigationdrawer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter2 (var context: Context,var list: MutableList<model2>):BaseAdapter()
{
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(postion: Int, convertView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)

        var view = layout.inflate(R.layout.grid_design,parent,false)

        var img:ImageView = view.findViewById(R.id.img)
        var txt:TextView =view.findViewById(R.id.txt)

        img.setImageResource(list.get(postion).Image)
        txt.setText(list.get(postion).Textview)



        return view

    }


}