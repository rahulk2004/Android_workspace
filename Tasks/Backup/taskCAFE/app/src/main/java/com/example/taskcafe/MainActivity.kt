package com.example.taskcafe

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var T2 :TextView
    lateinit var T3 :TextView

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        T2 =findViewById(R.id.t2)
        T3 =findViewById(R.id.t3)

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


        T2.setOnClickListener{

            var call ="8980073845"
            var c = Intent(Intent.ACTION_CALL)
            c.setData(Uri.parse("tel:"+call))
            startActivity(c)


        }

        T3.setOnClickListener{

            var url = "https://www.adcustoms.in/"
            var w =Intent(Intent.ACTION_VIEW)
            w.setData(Uri.parse(url))
            startActivity(w)
        }


        A1.setOnClickListener{
            var a = Intent(applicationContext,MainActivity2::class.java)
            startActivity(a)
        }

        A2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 359",Toast.LENGTH_LONG).show()
        }


        B1.setOnClickListener{
            var b = Intent(applicationContext,MainActivity3::class.java)
            startActivity(b)
        }

        B2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 180",Toast.LENGTH_LONG).show()
        }



        C1.setOnClickListener{
            var c = Intent(applicationContext,MainActivity4::class.java)
            startActivity(c)
        }

        C2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 80",Toast.LENGTH_LONG).show()
        }



        D1.setOnClickListener{
            var d= Intent(applicationContext,MainActivity5::class.java)
            startActivity(d)
        }

        D2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 150",Toast.LENGTH_LONG).show()
        }



        E1.setOnClickListener{
            var e = Intent(applicationContext,MainActivity6::class.java)
            startActivity(e)
        }

        E2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 179",Toast.LENGTH_LONG).show()
        }



        F1.setOnClickListener{
            var f = Intent(applicationContext,MainActivity7::class.java)
            startActivity(f)
        }

        F2.setOnClickListener{
            Toast.makeText(applicationContext,"Price : ₹ 99",Toast.LENGTH_LONG).show()
        }





    }

    override fun onBackPressed() {

        var alert = AlertDialog.Builder(this)
        alert.setTitle("are you sure you want to exit ?? ")
        alert.setPositiveButton("yes",{ dialogInterface : DialogInterface, i :Int -> finishAffinity()})
        alert.setNegativeButton("No",{ dialogInterface : DialogInterface, i :Int -> dialogInterface.cancel()})
        alert.show()
//          super.onBackPressed()
    }
}