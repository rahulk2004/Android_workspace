package com.example.taskdropdownradiobtn

import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener,
    RadioGroup.OnCheckedChangeListener {

    lateinit var spinner: Spinner
    lateinit var rg:RadioGroup
    lateinit var rb1:RadioButton
    lateinit var rb2:RadioButton

    var city = arrayOf("Rajkot","Baroda","Surat")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        spinner = findViewById(R.id.spin)
        rb1 = findViewById(R.id.rb1)
        rb2 = findViewById(R.id.rb2)
        rg=findViewById(R.id.rg)



        var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_spinner_dropdown_item,city)
        spinner.adapter=adapter

        spinner.setOnItemSelectedListener(this)

        rg.setOnCheckedChangeListener(this)



    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        Toast.makeText(applicationContext, ""+city[position], Toast.LENGTH_SHORT).show()

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }




    override fun onCheckedChanged(group: RadioGroup?, checkedInt: Int ) {


        if (rb1.isChecked==true){
            Toast.makeText(applicationContext, "Male", Toast.LENGTH_SHORT).show()
        }
        else if(rb2.isChecked==true){

            Toast.makeText(applicationContext, "Female", Toast.LENGTH_SHORT).show()
        }

    }


}