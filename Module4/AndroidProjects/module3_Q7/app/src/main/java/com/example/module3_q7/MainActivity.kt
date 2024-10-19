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

    lateinit var btn1:Button
    private lateinit var tableLayout: TableLayout
    private var rowCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn1 = findViewById(R.id.btn1)
        tableLayout = findViewById(R.id.Tablelayout)


        btn1.setOnClickListener{

            addNewRow()

        }



    }

    private fun addNewRow() {


        val tableRow = TableRow(this)


        val rankTextView = TextView(this).apply {
            text = (++rowCount).toString()
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        }
        val playerTextView = TextView(this).apply {
            text = "Player $rowCount"
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        }
        val teamTextView = TextView(this).apply {
            text = "Team $rowCount"
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        }
        val pointsTextView = TextView(this).apply {
            text = (800 + rowCount).toString() // Example points
            layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)
        }

        tableRow.addView(rankTextView)
        tableRow.addView(playerTextView)
        tableRow.addView(teamTextView)
        tableRow.addView(pointsTextView)

        tableLayout.addView(tableRow)
    }

}