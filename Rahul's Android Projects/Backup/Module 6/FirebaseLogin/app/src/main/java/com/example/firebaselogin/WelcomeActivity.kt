package com.example.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val user = FirebaseAuth.getInstance().currentUser
        tvWelcome.text = "Welcome, ${user?.email}!"
    }
}
