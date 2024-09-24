package com.example.taskwpclone

import android.content.Intent
import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity() : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var list: MutableList<model>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        listView =findViewById(R.id.list)
        list = ArrayList()


        list.add(model(R.drawable.rimg,"Rahul Kanara","Hy , How Are You ?","8:20 PM",1234,"abcs"))
        list.add(model(R.drawable.kimage,"Kapil Garaniya","Classes e kyare aavano ","9:10 AM",234,"abcs"))
        list.add(model(R.drawable.icn2,"TOPS tech Rajkot","your fees are pending","7:56 AM",1234,"abcs"))
        list.add(model(R.drawable.m2,"Batch Android ","no Lacture today","12:45 PM",1234,"abcs"))
        list.add(model(R.drawable.himg,"Hit K","sent Image","5:30 PM",1234,"abcs"))
        list.add(model(R.drawable.m1,"Jay TOPS","hy ","9:20 PM",1234,"abcs"))
        list.add(model(R.drawable.m1,"Devin Python","resume mokl","11:01 AM",1234,"abcs"))
        list.add(model(R.drawable.m2,"Keval ","hy ","9:10 AM",1234,"abcs"))
        list.add(model(R.drawable.icn1,"+918238845824 ","hy ","9:20 PM",1234,"abcs"))
        list.add(model(R.drawable.picn,"Keval ","hy ","9:20 PM",1234,"abcs"))





        var adapter = MyAdapter(applicationContext,list)
        listView.adapter = adapter



    }

}