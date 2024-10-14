package com.example.tasknavigationdrawer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast


class HomeFragment : Fragment() {

    lateinit var listView:GridView
    lateinit var list: MutableList<model2>

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_home2, container, false)

        listView = view.findViewById(R.id.list)
        list =ArrayList()

        list.add(model2(R.drawable.javalogo,"Java"))
        list.add(model2(R.drawable.kotlinlogo,"Kotlin"))
        list.add(model2(R.drawable.phplogo,"PHP"))
        list.add(model2(R.drawable.ioslogo,"Ios"))

        var adapter = MyAdapter2(requireActivity(),list)
        listView.adapter =adapter

        listView.setOnItemClickListener {
                parent, view, position, id ->



            if(position==0)
            {
                var f2 = JavaFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }
            if(position==1)
            {
                var f2 = KotlinFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }
            if(position==2)
            {
                var f2 = PhpFragment()
                var fm = fragmentManager
                var ft = fm!!.beginTransaction()
                ft.replace(R.id.nav_host_fragment_content_main,f2).commit()
            }

        }

        return view

    }


}