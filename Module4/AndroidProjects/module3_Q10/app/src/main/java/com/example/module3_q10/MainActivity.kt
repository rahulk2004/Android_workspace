package com.example.module3_q10

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

     lateinit var editTextNumber: EditText
     lateinit var buttonGenerate: Button
     lateinit var editTextContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editTextNumber = findViewById(R.id.editTextNumber)
        buttonGenerate = findViewById(R.id.buttonGenerate)
        editTextContainer = findViewById(R.id.editTextContainer)

        buttonGenerate.setOnClickListener{

            generateEditTexts()

        }



    }

    fun generateEditTexts() {
        // Clear existing EditTexts
        editTextContainer.removeAllViews()

        // Get the number input by the user
        val numberOfEditTexts = editTextNumber.text.toString().toIntOrNull()

        if (numberOfEditTexts != null && numberOfEditTexts > 0) {
            for (i in 1..numberOfEditTexts) {
                val editText = EditText(this)
                editText.hint = "EditText $i"
                editTextContainer.addView(editText)
            }
        }
    }


}