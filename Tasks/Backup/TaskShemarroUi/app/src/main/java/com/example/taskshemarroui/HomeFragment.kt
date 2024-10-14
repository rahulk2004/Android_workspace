package com.example.taskshemarroui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class HomeFragment : Fragment() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_home2, container, false)

        viewPager = view.findViewById(R.id.viewpager)
        setupviewpager()

        tabLayout = view.findViewById(R.id.tab)
        tabLayout.setupWithViewPager(viewPager)

        return view

    }

    private fun setupviewpager() {

        var adapter = MyAdapter(requireContext(),supportFragmentManager)

        adapter.tops("All",AllFragment())
        adapter.tops("Movies",MoviesFragment())
        adapter.tops("Shows",ShowsFragment())
        adapter.tops("Channels",ChannnelsFragment())
        adapter.tops("Live",LiveFragment())

        viewPager.adapter=adapter

    }


}