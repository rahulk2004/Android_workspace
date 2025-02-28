package com.example.ecomproject.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomproject.R

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        if (Build.VERSION.SDK_INT >= 21)
        {
            val window = this.window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            window.statusBarColor = this.resources.getColor(R.color.black)

        }

        Handler().postDelayed(Runnable {

            var i = Intent(applicationContext,LoginActivity::class.java)
            startActivity(i)

        },3000)
    }
}