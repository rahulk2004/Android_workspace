package com.example.indianpincodes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.indianpincodes.databinding.ActivityAddPinBinding
import com.google.firebase.database.FirebaseDatabase

class AddPinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddPinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAddPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnadd.setOnClickListener {
            val pincode = binding.edtpincode.text.toString()
            val officename = binding.edtofficename.text.toString()
            val district = binding.edtdistrict.text.toString()
            val state = binding.edtstate.text.toString()
            val officetype = binding.edtofficetype.text.toString()
            val deliverystatus = binding.edtdeliverystatus.text.toString()
            val taluka = binding.edttaluka.text.toString()
            val telephone = binding.edttel.text.toString()
            val headoffice = binding.edtheadoffice.text.toString()
            val division = binding.edtdivision.text.toString()
            val region = binding.edtregion.text.toString()
            val circle = binding.edtcircle.text.toString()

            val map = hashMapOf(
                "pincode" to pincode,
                "officename" to officename,
                "district" to district,
                "state" to state,
                "officetype" to officetype,
                "deliverystatus" to deliverystatus,
                "taluka" to taluka,
                "telephone" to telephone,
                "headoffice" to headoffice,
                "division" to division,
                "region" to region,
                "circle" to circle
            )

            FirebaseDatabase.getInstance().reference.child("pincodes").push()
                .setValue(map)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Data Added Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        super.onBackPressed()
    }
}
