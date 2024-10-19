package com.example.module3_q9

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var txt1:TextView
    lateinit var btn1:Button
    lateinit var btn2:Button

    var fontSize: Float = 16f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txt1 = findViewById(R.id.textView)
        btn1 = findViewById(R.id.buttonIncrease)
        btn2 = findViewById(R.id.buttonDecrease)

        btn1.setOnClickListener{

            fontSize += 2f
            txt1.textSize = fontSize

        }

        btn2.setOnClickListener{

            if (fontSize > 8f) {

                fontSize -= 2f
                txt1.textSize = fontSize
            }
        }
    }
}