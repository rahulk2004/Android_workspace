package com.example.taskfilemanager

import android.content.Context
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class musicAdapter(var context: Context,var list: MutableList<musicModel>):BaseAdapter()
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.music_design,parent,false)

        var txt1: TextView = view.findViewById(R.id.txt1)
        var txt2:TextView = view.findViewById(R.id.txt2)
        var txt3 :TextView = view.findViewById(R.id.txt3)

        txt1.setText(list.get(position).name)
        txt2.setText(list.get(position).size)
        txt3.setText(list.get(position).date)

        return view
    }


}