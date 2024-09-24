package com.example.taskamul

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var A1:ImageView
    lateinit var A2:TextView

    lateinit var B1:ImageView
    lateinit var B2:TextView

    lateinit var C1:ImageView
    lateinit var C2:TextView

    lateinit var D1:ImageView
    lateinit var D2:TextView

    lateinit var E1:ImageView
    lateinit var E2:TextView

    lateinit var F1:ImageView
    lateinit var F2:TextView

    lateinit var G1:ImageView
    lateinit var G2:TextView

    lateinit var H1:ImageView
    lateinit var H2:TextView

    lateinit var I1:ImageView
    lateinit var I2:TextView

    lateinit var J1:ImageView
    lateinit var J2:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        A1 = findViewById(R.id.a1)
        A2 = findViewById(R.id.a2)

        B1 = findViewById(R.id.b1)
        B2 = findViewById(R.id.b2)

        C1 = findViewById(R.id.c1)
        C2 = findViewById(R.id.c2)

        D1 = findViewById(R.id.d1)
        D2 = findViewById(R.id.d2)

        E1 = findViewById(R.id.e1)
        E2 = findViewById(R.id.e2)

        F1 = findViewById(R.id.f1)
        F2 = findViewById(R.id.f2)

        G1 = findViewById(R.id.g1)
        G2 = findViewById(R.id.g2)

        H1 = findViewById(R.id.h1)
        H2 = findViewById(R.id.h2)

        I1 = findViewById(R.id.i1)
        I2 = findViewById(R.id.i2)

        J1 = findViewById(R.id.j1)
        J2 = findViewById(R.id.j2)



        A1.setOnClickListener{
            Toast.makeText(applicationContext,"Vegitable Ghee",Toast.LENGTH_LONG).show()
        }
        A2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity2::class.java)
            startActivity(a)
        }

        B1.setOnClickListener{
            Toast.makeText(applicationContext,"Chocolate",Toast.LENGTH_LONG).show()
        }
        B2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity3::class.java)
            startActivity(a)
        }

        C1.setOnClickListener{
            Toast.makeText(applicationContext,"Milk Powder",Toast.LENGTH_LONG).show()
        }
        C2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity4::class.java)
            startActivity(a)
        }

        D1.setOnClickListener{
            Toast.makeText(applicationContext,"Mithai Mate",Toast.LENGTH_LONG).show()
        }
        D2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity5::class.java)
            startActivity(a)
        }

        E1.setOnClickListener{
            Toast.makeText(applicationContext,"Sports Drink",Toast.LENGTH_LONG).show()
        }
        E2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity6::class.java)
            startActivity(a)
        }

        F1.setOnClickListener{
            Toast.makeText(applicationContext,"Pure Honey",Toast.LENGTH_LONG).show()
        }
        F2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity7::class.java)
            startActivity(a)
        }

        G1.setOnClickListener{
            Toast.makeText(applicationContext,"Beverages",Toast.LENGTH_LONG).show()
        }
        G2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity8::class.java)
            startActivity(a)
        }

        H1.setOnClickListener{
            Toast.makeText(applicationContext,"Sweets",Toast.LENGTH_LONG).show()
        }
        H2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity9::class.java)
            startActivity(a)
        }

        I1.setOnClickListener{
            Toast.makeText(applicationContext,"Panchamrit",Toast.LENGTH_LONG).show()
        }
        I2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity10::class.java)
            startActivity(a)
        }

        J1.setOnClickListener{
            Toast.makeText(applicationContext,"Wiping Cream",Toast.LENGTH_LONG).show()
        }
        J2.setOnClickListener{
            var a = Intent(applicationContext,MainActivity11::class.java)
            startActivity(a)
        }


    }
}