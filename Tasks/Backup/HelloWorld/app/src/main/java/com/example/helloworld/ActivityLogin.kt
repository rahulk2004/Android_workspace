package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityLogin : AppCompatActivity() {

    lateinit var txt4:TextView
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt4 = findViewById(R.id.txt4)
        btn1 = findViewById(R.id.btn1)

        txt4.setOnClickListener{

            var i = Intent(applicationContext,ActivityRegister::class.java)
            startActivity(i)
        }

        btn1.setOnClickListener{

            var b = Intent(applicationContext,MainActivity::class.java)
            startActivity(b)
        }
    }
}