package com.example.task1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat.IntentBuilder
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText

    lateinit var btn1:Button
    lateinit var txt1:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1=findViewById(R.id.edt1)
        edt2=findViewById(R.id.edt2)

        btn1=findViewById(R.id.btn1)
        txt1=findViewById(R.id.txt1)

        btn1.setOnClickListener{

            var m1 = edt1.text.toString()
            var m2 =edt2.text.toString()


            var a = m1.toInt()
            var b = m2.toInt()
            var builder=StringBuilder()

//            Log.d("data1:",a.toString())


            for(i in a..b)
            {

                Log.d("data1:",i.toString())


                val lst = i

                builder.append(lst)

                var m = Intent(applicationContext,MainActivity2::class.java)
                m.putExtra("rahul",builder.toString())
                startActivity(m)


            }

        }
    }


}