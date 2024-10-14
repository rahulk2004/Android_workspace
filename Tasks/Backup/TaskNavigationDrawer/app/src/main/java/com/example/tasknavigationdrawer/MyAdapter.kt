package com.example.tasknavigationdrawer

import android.content.Context
import android.health.connect.datatypes.units.Power
import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MyAdapter(var context: Context,var list: MutableList<Model>):BaseAdapter() {

    override fun getCount(): Int {
        return list.size

    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {

        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design_java,parent,false)


        var txt:TextView = view.findViewById(R.id.txt)
        var desc:TextView = view.findViewById(R.id.desc)
        var constraintLayout:ConstraintLayout = view.findViewById(R.id.constraintLayout)

        txt.setText(list.get(position).topic)
        desc.setText(list.get(position).desc)


        return view

    }


}