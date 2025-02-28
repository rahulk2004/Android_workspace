package com.example.taskloginui

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    lateinit var edt1 :EditText
    lateinit var edt2:EditText
    lateinit var sbtn :Button
    lateinit var sharedPreferences: SharedPreferences

    var pressed:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.username)
        edt2 = findViewById(R.id.password)
        sbtn = findViewById(R.id.login_button)
        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)


        if(sharedPreferences.getBoolean("tops",false)&& !sharedPreferences.getString("e1","")!!.isEmpty())
        {
            var i = Intent(applicationContext,MainActivity2::class.java)
            startActivity(i)
            finish()
        }



        sbtn.setOnClickListener{

            var name = edt1.text.toString()
            var pass = edt2.text.toString()

            if(name.length==0 && pass.length==0)
            {
                edt1.setError("Please Enter Your Name ")
                edt2.setError("Please Enter Your Password ")

            }
            else if (name.length==0){

                edt1.setError("Please Enter Your Name ")

            }
            else if(pass.length==0){

                edt2.setError("please Enter Your Password ")
            }
            else{

                var s1 : SharedPreferences.Editor = sharedPreferences.edit()
                s1.putBoolean("tops",true)
                s1.putString("e1",name)
                s1.putString("p1",pass)
                s1.commit()

                var i = Intent(applicationContext,MainActivity2::class.java)
                startActivity(i)



            }


        }

    }


//    override fun onBackPressed()
//    {
//
//        super.onBackPressed()
//        finishAffinity()
//    }

    override fun onBackPressed() {

       if(pressed + 1000> System.currentTimeMillis()) {
           super.onBackPressed()
           finishAffinity()
       }
        else{

           Toast.makeText(applicationContext, "press back twice to exit", Toast.LENGTH_SHORT).show()
       }

        pressed = System.currentTimeMillis()
    }




}