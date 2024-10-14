package com.example.tasktoolbar

import android.Manifest.permission.CALL_PHONE
import android.content.ClipData.Item
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RouteListingPreference
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var toolbar:Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        checkPermission()
        if(checkPermission())
        {
            Toast.makeText(applicationContext, " permission granted ", Toast.LENGTH_SHORT).show()
        }
        else{

            Toast.makeText(applicationContext, "please allow the permission ", Toast.LENGTH_SHORT).show()

            requestpermission()
        }

        toolbar=findViewById(R.id.tool)
        setSupportActionBar(toolbar)


    }

    private fun checkPermission():Boolean {

        val b = ContextCompat.checkSelfPermission(this@MainActivity, CALL_PHONE)

        return b==PackageManager.PERMISSION_GRANTED

    }

    private  fun requestpermission(){

        ActivityCompat.requestPermissions(this@MainActivity, arrayOf(CALL_PHONE),200)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.i1->
            {

                var call ="8980073845"
                var c = Intent(Intent.ACTION_CALL)
                c.setData(Uri.parse("tel:"+call))
                startActivity(c)




            }

            R.id.i2->
            {

                var url = "https://www.w3schools.com/KOTLIN/index.php"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(url))
                startActivity(w)
            }

            R.id.i3->
            {

                var url = "https://www.w3schools.com/java/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(url))
                startActivity(w)
            }

            R.id.i4->
            {

                var url = "https://www.w3schools.com/python/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(url))
                startActivity(w)
            }

            R.id.i5->
            {

                var url = "https://www.w3schools.com/c/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(url))
                startActivity(w)
            }

            R.id.i6->
            {

                var url = "https://www.w3schools.com/cpp/"
                var w = Intent(Intent.ACTION_VIEW)
                w.setData(Uri.parse(url))
                startActivity(w)
            }

        }
        return super.onOptionsItemSelected(item)


    }
}