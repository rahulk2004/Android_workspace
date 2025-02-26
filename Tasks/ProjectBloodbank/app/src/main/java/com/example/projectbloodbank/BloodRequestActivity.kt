package com.example.projectbloodbank

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.projectbloodbank.databinding.ActivityBloodRequestBinding

class BloodRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBloodRequestBinding
    private lateinit var db: DatabseClass
    private lateinit var list: MutableList<BloodRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBloodRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(applicationContext, DatabseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()

        val sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE)
        val loggedInUserId = sharedPreferences.getInt("userId", -1)

        if (loggedInUserId != -1) {
            list = db.BloodRequestDao().fetchByUserId(loggedInUserId)
        } else {
            list = mutableListOf()
        }

        val layoutManager = LinearLayoutManager(this)
        binding.recycler.layoutManager = layoutManager

        val adapter = BloodReqAdapter(applicationContext, list)
        binding.recycler.adapter = adapter

        binding.f1.setOnClickListener {
            startActivity(Intent(this, AddBloodReqActivity::class.java))
        }
    }
}
