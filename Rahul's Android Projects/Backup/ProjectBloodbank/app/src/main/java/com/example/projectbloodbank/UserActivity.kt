package com.example.projectbloodbank

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projectbloodbank.databinding.ActivityMainBinding
import com.example.projectbloodbank.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE)


        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        if (!isLoggedIn) {

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.logout.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        binding.bloodRequestLayout.setOnClickListener {

            startActivity(Intent(this,BloodRequestActivity::class.java))
        }

        binding.bloodBanksLayout.setOnClickListener {

            startActivity(Intent(this,BloodbankActivity::class.java))
        }

        binding.callButton.setOnClickListener {
            makeEmergencyCall()
        }


    }

    private fun makeEmergencyCall() {
        val emergencyNumber = "112"
        val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$emergencyNumber"))
        startActivity(dialIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            makeEmergencyCall()
        } else {
            Toast.makeText(this, "Permission denied to make calls", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to logout?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->

                sharedPreferences.edit().clear().apply()


                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }


        val alert = builder.create()
        alert.show()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}