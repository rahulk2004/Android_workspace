package com.example.helloworld

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FirstFragment : Fragment() {

    lateinit var txt1:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_first, container, false)

        txt1=view.findViewById(R.id.txt1)

        txt1.setOnClickListener{

            //Fragment to Activity

            var i = Intent(activity,MainActivity3::class.java)
            startActivity(i)

        }

        return view
    }


    override fun onAttach(context: Context) {
        Toast.makeText(activity, "F Attached", Toast.LENGTH_SHORT).show()
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Toast.makeText(activity, "F Created", Toast.LENGTH_SHORT).show()
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Toast.makeText(activity, "F View Created", Toast.LENGTH_SHORT).show()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Toast.makeText(activity, "F Started", Toast.LENGTH_SHORT).show()
        super.onStart()
    }

    override fun onResume() {
        Toast.makeText(activity, "F Resumed", Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onPause() {
        Toast.makeText(activity, "F Paused", Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onStop() {
        Toast.makeText(activity, "F Stopped", Toast.LENGTH_SHORT).show()
        super.onStop()
    }

    override fun onDestroyView() {
        Toast.makeText(activity, "F Destroyed View", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
    }

    override fun onDestroy() {
        Toast.makeText(activity, "F Destroyed", Toast.LENGTH_SHORT).show()

        super.onDestroy()
    }

    override fun onDetach() {
        Toast.makeText(activity, "F Detached", Toast.LENGTH_SHORT).show()
        super.onDetach()
    }


}