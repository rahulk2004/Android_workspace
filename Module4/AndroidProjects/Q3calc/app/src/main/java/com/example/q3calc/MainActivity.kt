package com.example.q3calc

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

// Q3 Create an application with radio buttons (Add, Subtraction, Multiply, Division) EditText (number1, number2) and print result as per user choice from radio button in TextView

    lateinit var edt1:EditText
    lateinit var edt2:EditText

    lateinit var add:RadioButton
    lateinit var sub:RadioButton
    lateinit var mul:RadioButton
    lateinit var div:RadioButton

    lateinit var txt1:TextView


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
        edt2 = findViewById(R.id.edt2)

       add = findViewById(R.id.radd)
        sub = findViewById(R.id.sub)
        mul = findViewById(R.id.mul)
        div = findViewById(R.id.div)

        txt1 = findViewById(R.id.txt1)

        add.setOnClickListener{

            add()
        }

        sub.setOnClickListener{

            sub()

        }

        mul.setOnClickListener{

            mul()

        }

        div.setOnClickListener{

            div()

        }




    }

    fun add(){

        var inputdata1=edt1.text.toString().trim().toBigDecimal()
        var inputdata2=edt2.text.toString().trim().toBigDecimal()
        txt1.text=inputdata1.add(inputdata2).toString()

    }

    fun sub(){

        var inputdata1=edt1.text.toString().trim().toBigDecimal()
        var inputdata2=edt2.text.toString().trim().toBigDecimal()
        txt1.text=inputdata1.subtract(inputdata2).toString()

    }


    fun mul(){

        var inputdata1=edt1.text.toString().trim().toBigDecimal()
        var inputdata2=edt2.text.toString().trim().toBigDecimal()
        txt1.text=inputdata1.multiply(inputdata2).toString()

    }


    fun div(){

        var inputdata1=edt1.text.toString().trim().toBigDecimal()
        var inputdata2=edt2.text.toString().trim().toBigDecimal()
        txt1.text=inputdata1.divide(inputdata2).toString()

    }


}