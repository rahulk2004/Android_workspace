package com.example.module3_q10

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

//  Q10 create an application to display n edittext where n is number input by user

    lateinit var edt1: EditText
     lateinit var btn1: Button
     lateinit var txt: LinearLayout

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

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        txt = findViewById(R.id.txt)

        btn1.setOnClickListener{

            generateEditTexts()

        }



    }

    fun generateEditTexts() {

        txt.removeAllViews()

        val numberOfEditTexts = edt1.text.toString().toIntOrNull()

        if (numberOfEditTexts != null && numberOfEditTexts > 0) {
            for (i in 1..numberOfEditTexts) {
                val editText = EditText(this)
                editText.hint = "EditText"
                txt.addView(editText)
            }
        }
    }


}