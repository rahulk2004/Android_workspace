package com.example.taskfilemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView


class ImageFragment : Fragment() {

    lateinit var listView: GridView
    lateinit var list: MutableList<imgModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_image, container, false)

        listView = view.findViewById(R.id.list)
        list = ArrayList()

        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))
        list.add(imgModel(R.drawable.img1,"Flowers","200"))




        var adapter = imgAdapter(requireActivity(),list)
        listView.adapter = adapter

        return view


    }


}