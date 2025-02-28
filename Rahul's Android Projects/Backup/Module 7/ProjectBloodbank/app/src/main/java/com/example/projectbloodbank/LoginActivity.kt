package com.example.projectbloodbank

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.projectbloodbank.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var db: DatabseClass
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE)


        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            val userType = sharedPreferences.getString("userType", "user")
            navigateToNextScreen(userType)
        }


        db = Room.databaseBuilder(applicationContext, DatabseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()


        binding.loginButton.setOnClickListener {
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()


            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val user = db.UserDao().getUserByEmail(email)
            if (user != null && user.upass == password) {

                val editor = sharedPreferences.edit()
                editor.putBoolean("isLoggedIn", true)
                editor.putString("userType", user.utype)
                editor.putString("username", user.uname)
                editor.putInt("userId", user.uid)
                editor.apply()


                navigateToNextScreen(user.utype)
                finish()
            } else {

                Toast.makeText(this, "Invalid Email or Password", Toast.LENGTH_SHORT).show()
            }
        }


        binding.signupLink.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun navigateToNextScreen(userType: String?) {
        val userId = sharedPreferences.getInt("userId", -1)
        when (userType) {
            "admin" -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userId", userId)
                startActivity(intent)
            }
            "user" -> {
                val intent = Intent(this, UserActivity::class.java)
                intent.putExtra("userId", userId)
                startActivity(intent)
            }
            else -> {
                Toast.makeText(this, "Invalid user type", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
