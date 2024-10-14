package com.example.tasktablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class ChatFragment : Fragment() {


    lateinit var listView: ListView
    lateinit var list: MutableList<Model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_chat, container, false)


        listView =view.findViewById(R.id.list)
        list = ArrayList()


        list.add(Model(R.drawable.rimg,"Rahul Kanara","Hy , How Are You ?","8:20 PM",1234,"abcs"))
        list.add(Model(R.drawable.kimage,"Kapil Garaniya","Classes e kyare aavano ","9:10 AM",234,"abcs"))
        list.add(Model(R.drawable.icn2,"TOPS tech Rajkot","your fees are pending","7:56 AM",1234,"abcs"))
        list.add(Model(R.drawable.m2,"Batch Android ","no Lacture today","12:45 PM",1234,"abcs"))
        list.add(Model(R.drawable.himg,"Hit K","sent Image","5:30 PM",1234,"abcs"))
        list.add(Model(R.drawable.m1,"Jay TOPS","hy ","9:20 PM",1234,"abcs"))
        list.add(Model(R.drawable.m1,"Devin Python","resume mokl","11:01 AM",1234,"abcs"))
        list.add(Model(R.drawable.m2,"Keval ","hy ","9:10 AM",1234,"abcs"))
        list.add(Model(R.drawable.icn1,"+918238845824 ","hy ","9:20 PM",1234,"abcs"))
        list.add(Model(R.drawable.picn,"Keval ","hy ","9:20 PM",1234,"abcs"))





        var adapter = MyAdapter2(requireActivity(),list)
        listView.adapter = adapter

        return view



    }

}