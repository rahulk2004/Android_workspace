package com.example.ecomproject.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ecomproject.Model.RegisterModel
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityLoginBinding
import com.example.ecomproject.interfaces.ApiInterface
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var apiInterface: ApiInterface
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleSignInResult(task)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.black)
        }

        sharedPreferences = getSharedPreferences("user_session", Context.MODE_PRIVATE)
        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)

        if (sharedPreferences.getBoolean("user_session", false) &&
            sharedPreferences.getString("mob", "")!!.isNotEmpty()) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }

        binding.btnLogIn.setOnClickListener { loginWithCredentials() }
        binding.sig.setOnClickListener { signInWithGoogle() }
        binding.textView3.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun loginWithCredentials() {
        val phone = binding.edtPhone.text.toString()
        val pass = binding.edtPassword.text.toString()

        if (phone.length != 10) {
            binding.edtPhone.error = "Please Enter Proper Phone Number"
        } else if (pass.isEmpty()) {
            binding.edtPassword.error = "Please Enter Password"
        } else {
            val call: Call<RegisterModel> = apiInterface.logindata(phone, pass)
            call.enqueue(object : Callback<RegisterModel> {
                override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                    sharedPreferences.edit().putString("mob", phone).apply()
                    sharedPreferences.edit().putBoolean("user_session", true).apply()
                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
                    startActivity(Intent(applicationContext, DashboardActivity::class.java))
                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        signInLauncher.launch(signInIntent)
    }

    private fun handleSignInResult(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            Log.d("GoogleSignIn", "Sign-in successful: ${account.email}")
            Toast.makeText(applicationContext, "Welcome ${account.displayName}", Toast.LENGTH_LONG).show()


            sharedPreferences.edit().putString("user_name", account.displayName).apply()
            sharedPreferences.edit().putString("user_email", account.email).apply()


            startActivity(Intent(applicationContext, DashboardActivity::class.java))
            finish()

        } catch (e: ApiException) {
            Log.e("GoogleSignIn", "Sign-in failed: ${e.statusCode}", e)
            Toast.makeText(applicationContext, "Google Sign-In Failed: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
}
