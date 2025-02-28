package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvRedirectSignUp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.etEmailAddress)
        etPass = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRedirectSignUp = findViewById(R.id.tvRedirectSignUp)

        btnLogin.setOnClickListener { login() }
        tvRedirectSignUp.setOnClickListener { startActivity(Intent(this, SignUpActivity::class.java)) }
    }

    private fun login() {
        val email = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Welcome ${user?.email}!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Login Failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
