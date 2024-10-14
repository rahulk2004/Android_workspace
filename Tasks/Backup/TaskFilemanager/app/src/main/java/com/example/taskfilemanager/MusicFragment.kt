package com.example.taskfilemanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView


class MusicFragment : Fragment() {

    lateinit var listView: ListView
    lateinit var list: MutableList<musicModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_music, container, false)

        listView = view.findViewById(R.id.list)
        list = ArrayList()

        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))
        list.add(musicModel("+91 89800 73845","1.5 MB","2 October"))

        var adapter = musicAdapter(requireActivity(),list)
        listView.adapter = adapter

        return view

    }


}