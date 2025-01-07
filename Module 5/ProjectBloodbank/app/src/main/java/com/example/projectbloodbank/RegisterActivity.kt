package com.example.projectbloodbank

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.projectbloodbank.databinding.ActivityLoginBinding
import com.example.projectbloodbank.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var edt3: EditText
    lateinit var btn1: Button
    lateinit var db:DatabseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.name)
        edt2 = findViewById(R.id.email)
        edt3 = findViewById(R.id.password)
        btn1 = findViewById(R.id.login_button)

        db = Room.databaseBuilder(applicationContext,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        btn1.setOnClickListener{

            var name = edt1.text.toString()
            var email = edt2.text.toString()
            var password = edt3.text.toString()

            var u = User()
            u.uname = name
            u.uemail = email
            u.upass = password

            db.UserDao().uinsert(u)

            Toast.makeText(applicationContext, " Inserted", Toast.LENGTH_SHORT).show()

            startActivity(Intent(applicationContext,MainActivity::class.java))

        }

        binding.signupLink.setOnClickListener{

            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }

    }
}