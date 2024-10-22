package com.example.module3_q11

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

// Q11  Create an application to display Textview when checkbox is checked and hide otherwise

    lateinit var txt1:TextView
    lateinit var chk1:CheckBox

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

        txt1 = findViewById(R.id.txt1)
        chk1 = findViewById(R.id.chk1)

        chk1.setOnCheckedChangeListener { _, isChecked ->
            updateTextViewVisibility()
        }

    }

    private fun updateTextViewVisibility() {
        txt1.visibility = if (chk1.isChecked) android.view.View.GONE else android.view.View.VISIBLE
    }
}