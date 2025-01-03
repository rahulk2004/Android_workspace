package com.example.taskretrofit2

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var edt1: EditText
    lateinit var edt2:EditText
    lateinit var btn2: Button
    lateinit var apiinterface: Apiinterface
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edtemail)
        edt2 = findViewById(R.id.edtpass)
        btn2 = findViewById(R.id.btnlogin)

        apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)

        btn1 = findViewById(R.id.btn1)

        btn2.setOnClickListener{

            var e = edt1.text.toString()
            var p = edt2.text.toString()

            var call: Call<Model> = apiinterface.logindata(e,p)
            call.enqueue(object :Callback<Model>{
                override fun onResponse(call: retrofit2.Call<Model>, response: Response<Model>) {

                    Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext,MainActivity::class.java) )
                }

                override fun onFailure(call: retrofit2.Call<Model>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login Fail", Toast.LENGTH_SHORT).show()
                }

            })



        }
        btn1.setOnClickListener{

            startActivity(Intent(applicationContext,RegisterActivity::class.java) )


        }

    }
}