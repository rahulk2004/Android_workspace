package com.example.module3_q7

import android.os.Bundle
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

//  Q7 Create an application to add TextView in TableLayout Programmatically

    lateinit var tableLayout: TableLayout
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tableLayout = findViewById(R.id.tableLayout)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
            addTextView()
        }


    }

    private fun addTextView() {

        val textView = TextView(this).apply { text = "TOPS TECH" }
        tableLayout.addView(TableRow(this).apply { addView(textView) })
    }

}