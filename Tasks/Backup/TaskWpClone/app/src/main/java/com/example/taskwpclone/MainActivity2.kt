package com.example.taskwpclone

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity2 : AppCompatActivity() {

    lateinit var img:ImageView
    lateinit var nm:TextView
    lateinit var num:TextView
    lateinit var category: TextView



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        var i = intent
        var pos = i.getIntExtra("pos",101)


        img=findViewById(R.id.dpimg)
        nm =findViewById(R.id.dpnm)
        num =findViewById(R.id.dpnum)
        category =findViewById(R.id.dpcntry)

        img.setImageResource(i.getIntExtra("img",202))
        nm.setText(i.getStringExtra("name"))
        num.setText(i.getIntExtra("num",101).toString())
        category.setText(i.getStringExtra("cate"))







        //var pos2= pos!!.toInt()\


//        if(pos==0){
//
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//        }
//
//        if(pos==1){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==2){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==3){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==4){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==5){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==6){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }
//
//        if(pos==7){
//
//            Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()
//
//
//        }



    }
}