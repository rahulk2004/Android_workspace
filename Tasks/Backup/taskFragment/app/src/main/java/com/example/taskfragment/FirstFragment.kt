package com.example.taskfragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FirstFragment : Fragment() {

    lateinit var txt1:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        txt1.setOnClickListener{



        var f2 = SecondFragment()
        var fm =fragmentManager
        var ft =fm!!.beginTransaction()
        ft.replace(R.id.frmid,f2).commit()
    }

    return view
}


}