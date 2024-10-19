package com.example.module3_q14

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var btn1:RadioButton
    lateinit var btn2:RadioButton
    lateinit var btn3:RadioButton
    lateinit var mainLayout:LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainLayout = findViewById(R.id.main)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)

        btn1.setOnClickListener {
            mainLayout.setBackgroundColor(getColor(R.color.red))
        }

        btn2.setOnClickListener {
            mainLayout.setBackgroundColor(getColor(R.color.green))
        }

        btn3.setOnClickListener {
            mainLayout.setBackgroundColor(getColor(R.color.blue))
        }



    }
}