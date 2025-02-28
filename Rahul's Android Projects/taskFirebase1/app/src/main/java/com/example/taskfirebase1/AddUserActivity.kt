package com.example.taskfirebase1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class AddUserActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn1:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_user)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edtname)
        edt2 = findViewById(R.id.edtsname)
        edt3 = findViewById(R.id.edtemail)
        edt4 = findViewById(R.id.edtpassword)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {

            var name = edt1.text.toString()
            var sname = edt2.text.toString()
            var email = edt3.text.toString()
            var pass = edt4.text.toString()

            var map = HashMap<String,String>()
            map["name"]=name
            map["sname"]=sname
            map["email"]=email
            map["pass"]=pass

            var db = FirebaseDatabase.getInstance()
                .getReference()
                .child("tops")
                .push()
                .setValue(map)

                .addOnSuccessListener {

                    Toast.makeText(applicationContext, "Inserted", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))

                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }



        }
}}