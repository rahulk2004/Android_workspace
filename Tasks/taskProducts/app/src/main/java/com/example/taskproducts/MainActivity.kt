package com.example.taskproducts

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
        listView = findViewById(R.id.gridview)
        list = ArrayList()

        sharedPreferences = getSharedPreferences("tops", MODE_PRIVATE)


        var stringRequest = StringRequest(
            Request.Method.GET,"https://prakrutitech.buzz/Rahul/view.php",
            object: Response.Listener<String>
            {
                override fun onResponse(response: String?) {
                    var jsonarray = JSONArray(response)
                    for (i in 0 until jsonarray.length())
                    {
                        var jsonObject = jsonarray.getJSONObject(i)

                        var name = jsonObject.getString("pname")
                        var price = jsonObject.getString("pprice")
                        var status = jsonObject.getString("pstatus")
                        var desc = jsonObject.getString("pdesc")
                        var image = jsonObject.getString("pimage")

                        var m = Model()
                        m.name = name
                        m.price = price
                        m.status = status
                        m.desc = desc
                        m.image = image

                        list.add(m)
                    }

                    var myadapter = MyAdapter(applicationContext,list)
                    listView.adapter = myadapter
                }
            },
            object : Response.ErrorListener
            {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                }

            }

        )

        var queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(stringRequest)

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

        }

        return super.onOptionsItemSelected(item)
    }
}
