package com.example.taskproducts

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var sharedPreferences: SharedPreferences
    lateinit var listView: GridView
    lateinit var list: MutableList<Model>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.tool)



        setSupportActionBar(toolbar)


        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)

        var f1 = AllFragment()
        var fm = supportFragmentManager
        var ft = fm.beginTransaction()
        ft.replace(R.id.frmid,f1).commit()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.option_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){

            R.id.i1->
            {
                sharedPreferences.edit().clear().commit()
                var i = Intent(applicationContext,LoginActivity::class.java)
                startActivity(i)
                finish()
            }

            R.id.i2 ->
            {
                //var dialog = Dialog()
                var alertDialog = AlertDialog.Builder(this)


                alertDialog.setPositiveButton("Low to High",{ dialogInterface: DialogInterface, i: Int ->

                    var f1 = LtoHFragment()
                    var fm = supportFragmentManager
                    var ft = fm.beginTransaction()
                    ft.replace(R.id.frmid,f1).commit()

                })

                alertDialog.setNegativeButton("High to Low",{ dialogInterface: DialogInterface, i: Int ->


                    var f1 = HtoLFragment()
                    var fm = supportFragmentManager
                    var ft = fm.beginTransaction()
                    ft.replace(R.id.frmid,f1).commit()

                })

                alertDialog.show()
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
