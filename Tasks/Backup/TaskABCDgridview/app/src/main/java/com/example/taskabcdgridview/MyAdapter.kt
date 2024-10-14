package com.example.taskabcdgridview

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator.Position
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MyAdapter(var context:Context,var list: MutableList<Model>):BaseAdapter() {

    override fun getCount(): Int {

        return list.size

    }

    override fun getItem(position: Int): Any {

        return position

    }

    override fun getItemId(position: Int): Long {

        return position.toLong()

    }

    override fun getView(position: Int, convertVie: View?, parent: ViewGroup?): View
    {
        var layout =LayoutInflater.from(context)
        var view =layout.inflate(R.layout.design,parent,false)

        var img:ImageView =view.findViewById(R.id.gridimg)
        var txt:TextView = view.findViewById(R.id.itemname)

        img.setImageResource(list.get(position).Image)
        txt.setText(list.get(position).Txt)

        return view


    }
}