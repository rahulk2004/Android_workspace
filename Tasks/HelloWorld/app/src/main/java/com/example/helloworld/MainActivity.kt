package com.example.helloworld

import android.R.color.holo_blue_light
import android.R.color.holo_green_dark
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var btn3:Button

    lateinit var edt1:EditText
    lateinit var btn4:Button

    lateinit var btn5:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1=findViewById(R.id.btn1)
        btn2=findViewById(R.id.btn2)
        btn3=findViewById(R.id.btn3)

        edt1=findViewById(R.id.edt1)
        btn4=findViewById(R.id.btn4)

        btn5=findViewById(R.id.btn5)


        btn1.setOnClickListener(){

            window.decorView.setBackgroundColor(Color.RED)

            Toast.makeText(applicationContext,"NOW ITS RED",Toast.LENGTH_LONG).show()
        }

        btn2.setOnClickListener(){

            window.decorView.setBackgroundColor(Color.GREEN)

            Toast.makeText(applicationContext,"NOW ITS GREEN",Toast.LENGTH_LONG).show()
        }

        btn3.setOnClickListener(){

            window.decorView.setBackgroundColor(Color.BLUE)

            Toast.makeText(applicationContext,"NOW ITS BLUE",Toast.LENGTH_LONG).show()
        }



        btn4.setOnClickListener{

            callActivity()
        }

        btn5.setOnClickListener{


            //Activity to Fragment

            var f1 = FirstFragment()
            var fm = supportFragmentManager
            var ft = fm.beginTransaction()
            ft.replace(R.id.frmid,f1).commit()



        }

    }

    private fun callActivity() {

        val message = edt1.text.toString()

        val Intent =Intent(applicationContext,MainActivity2::class.java).also {

            it.putExtra("EXTRA_MESSAGE",message)
            startActivity(it)


        }

    }

    //Activity Lifecycle

   override fun onStart(){
       Toast.makeText(applicationContext,"Started",Toast.LENGTH_LONG).show()
       super.onStart()
   }

    override fun onPause() {

        Toast.makeText(applicationContext,"Pause",Toast.LENGTH_SHORT).show()
        super.onPause()
    }

    override fun onResume() {
        Toast.makeText(applicationContext,"Resumed",Toast.LENGTH_SHORT).show()
        super.onResume()
    }

    override fun onRestart() {
        Toast.makeText(applicationContext, "Restarted", Toast.LENGTH_SHORT).show()
        super.onRestart()
    }

    override fun onDestroy() {
        Toast.makeText(applicationContext, "Destroyed", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    override fun onStop() {
        Toast.makeText(applicationContext, "Stopped", Toast.LENGTH_SHORT).show()
        super.onStop()
    }









}