package com.example.firebaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var etConfPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var tvRedirectLogin: TextView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etEmail = findViewById(R.id.etSEmailAddress)
        etPass = findViewById(R.id.etSPassword)
        etConfPass = findViewById(R.id.etSConfPassword)
        btnSignUp = findViewById(R.id.btnSSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        auth = Firebase.auth

        btnSignUp.setOnClickListener { signUpUser() }
        tvRedirectLogin.setOnClickListener { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    private fun signUpUser() {
        val email = etEmail.text.toString().trim()
        val pass = etPass.text.toString().trim()
        val confirmPassword = etConfPass.text.toString().trim()

        if (email.isEmpty() || pass.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(this, "Welcome ${user?.email}!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
