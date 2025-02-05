package com.example.module6q4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}
