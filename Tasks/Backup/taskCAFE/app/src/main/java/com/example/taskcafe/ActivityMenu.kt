package com.example.taskcafe

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityMenu : AppCompatActivity() {


    lateinit var chk1:CheckBox
    lateinit var chk2:CheckBox
    lateinit var chk3:CheckBox
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        chk1=findViewById(R.id.chk1)
        chk2=findViewById(R.id.chk2)
        chk3=findViewById(R.id.chk3)

        btn1=findViewById(R.id.btn1)

        btn1.setOnClickListener{

            var amount = 0
            var builder=StringBuilder("\n Selected Items")

            if(chk1.isChecked)
            {
                amount+=100
                builder.append("\n Pizza @ Rs. 100")
            }

            if (chk2.isChecked){

                amount+=70
                builder.append("\n Burger @ Rs. 70")
            }

            if(chk3.isChecked){

                amount+=120
                builder.append("\n Coffe @ Rs. 120")
            }

            builder.append("\n Total :"+amount)

            Toast.makeText(application,""+builder.toString(),Toast.LENGTH_LONG).show()

            var i = Intent(applicationContext,ActivityBill::class.java)
            i.putExtra("rahul",builder.toString())
            startActivity(i)


        }
    }
}