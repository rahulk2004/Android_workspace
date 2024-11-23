package com.example.taskproducts

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context,var list: MutableList<Model>):BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(p0: Int): Any {
        return list.get(p0)
    }

    override fun getItemId(p0: Int): Long {
       return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val layout = LayoutInflater.from(context)
        val view = layout.inflate(R.layout.design, p2, false)

        val txt1: TextView = view.findViewById(R.id.itemname)
        val txt2: TextView = view.findViewById(R.id.itemprice)
        val txt3: TextView = view.findViewById(R.id.itemstatus)
        val img: ImageView = view.findViewById(R.id.itemimage)

        val product = list[p0]
        txt1.text = product.name
        txt2.text = product.price
        txt3.text = product.status

        Log.d("ProductDetails", "Name: ${product.name}, Price: ${product.price}, Status: ${product.status}, Image URL: ${product.image}")

        Picasso.get().load(product.image).into(img)

        return view
    }

}