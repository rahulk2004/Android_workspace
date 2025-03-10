package com.example.tasktablayout

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


class MyAdapter( context: Context, fm: FragmentManager):FragmentStatePagerAdapter(fm){

    var listFragment:ArrayList<Fragment> = ArrayList()
    var listtitle:ArrayList<String> = ArrayList()


    override fun getCount(): Int {

        return listtitle.size

    }

    override fun getItem(position: Int): Fragment {

        return listFragment.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return listtitle.get(position)
    }

    fun tops(title:String,fragment: Fragment){

        listtitle.add(title)
        listFragment.add(fragment)

    }


}