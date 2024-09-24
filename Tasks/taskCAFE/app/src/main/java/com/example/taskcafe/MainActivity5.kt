package com.example.taskcafe

import android.content.DialogInterface
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onBackPressed() {

        var alert = AlertDialog.Builder(this)
        alert.setTitle("are you sure you want to exit ?? ")
        alert.setPositiveButton("yes",{ dialogInterface : DialogInterface, i :Int -> finish()})
        alert.setNegativeButton("No",{ dialogInterface : DialogInterface, i :Int -> dialogInterface.cancel()})
        alert.show()
//          super.onBackPressed()
    }
}