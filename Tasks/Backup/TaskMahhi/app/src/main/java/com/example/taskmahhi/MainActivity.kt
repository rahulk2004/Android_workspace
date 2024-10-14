package com.example.taskmahhi

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daimajia.slider.library.SliderLayout
import java.util.HashMap

class MainActivity : AppCompatActivity() {

    lateinit var listView:GridView
    lateinit var list: MutableList<Model>
    lateinit var toolbar: Toolbar
    lateinit var sliderLayout:SliderLayout
    var map = HashMap<String,Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sliderLayout = findViewById(R.id.slider)

        map.put("pizza",R.drawable.p4img)
        map.put("Coffe",R.drawable.p5img)
        map.put("Snadwich",R.drawable.p6img)

       

        toolbar = findViewById(R.id.tool)
        setSupportActionBar(toolbar)

        listView=findViewById(R.id.griview)
        list = ArrayList()

        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))
        list.add(Model("MILK",R.drawable.c1img,"RS 150 "))

        var adapter =MyAdapter(applicationContext,list)
        listView.adapter = adapter



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.optionmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}