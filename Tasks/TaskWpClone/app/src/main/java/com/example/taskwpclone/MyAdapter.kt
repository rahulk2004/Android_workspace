package com.example.taskwpclone

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MyAdapter(var context: Context,var list: MutableList<model>):BaseAdapter()
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
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        var layout = LayoutInflater.from(context)
        var view = layout.inflate(R.layout.design,parent,false)

        var img:ImageView = view.findViewById(R.id.img)
        var txt1:TextView = view.findViewById(R.id.txt1)
        var txt2:TextView = view.findViewById(R.id.txt2)
        var txt3:TextView = view.findViewById(R.id.txt3)

//        var num:TextView = view.findViewById(R.id.dpnum)
//        var cntry:TextView = view.findViewById(R.id.dpcntry)

        img.setImageResource(list.get(position).image)
        txt1.setText(list.get(position).txt1)
        txt2.setText(list.get(position).txt2)
        txt3.setText(list.get(position).txt3)

//        num.setText(list.get(position).number)
//        cntry.setText(list.get(position).category)

        view.setOnClickListener{

            var i = Intent(context,MainActivity2::class.java)
            i.putExtra("pos",position)
            i.putExtra("img",list.get(position).image)
            i.putExtra("name",list.get(position).txt1)
            i.putExtra("num",list.get(position).number)
            i.putExtra("cate",list.get(position).category)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(i)
        }

        return view



    }


}