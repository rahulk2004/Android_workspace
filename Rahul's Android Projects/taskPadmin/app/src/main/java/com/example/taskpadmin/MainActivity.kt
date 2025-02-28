package com.example.taskpadmin

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var f1:FloatingActionButton
    lateinit var sharedPreferences: SharedPreferences
    lateinit var listView: ListView
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
        f1 = findViewById(R.id.f1)

        setSupportActionBar(toolbar)
        listView = findViewById(R.id.listview)
        list = ArrayList()


        registerForContextMenu(listView)

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

                        var id = jsonObject.getString("pid")
                        var name = jsonObject.getString("pname")
                        var price = jsonObject.getString("pprice")
                        var status = jsonObject.getString("pstatus")
                        var desc = jsonObject.getString("pdesc")
                        var image = jsonObject.getString("pimage")

                        var m = Model()
                        m.id = id
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

        f1.setOnClickListener{
            var i = Intent(applicationContext,AddProductActivity::class.java)
            startActivity(i)

        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        var m1 = menu!!.add(0,1,0,"update")
        var m2 = menu!!.add(0,2,0,"Delete")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var acm: AdapterContextMenuInfo = item.menuInfo as AdapterContextMenuInfo

        var id = acm.position
        var m = list[id]

        when(item.itemId)
        {
            1->
            {
                var i = Intent(applicationContext,UpdateProductActivity::class.java)
                i.putExtra("pid",m.id)
                i.putExtra("pname",m.name)
                i.putExtra("pprice",m.price)
                i.putExtra("pdesc",m.desc)
                i.putExtra("pstatus",m.status)
                startActivity(i)
            }
            2->
            {
                var alert = AlertDialog.Builder(this)
                alert.setTitle("Are you sure you want to delete ?")
                alert.setPositiveButton("YES",
                    { dialogInterface: DialogInterface, i:Int ->

                        var stringrequest:StringRequest = object :StringRequest(Request.Method.POST,"https://prakrutitech.buzz/Rahul/delete.php",

                            Response.Listener {

                                Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(applicationContext,MainActivity::class.java))
                            },Response.ErrorListener {
                                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
                            }


                        )
                        {
                            override fun getParams(): MutableMap<String, String>? {

                                var map = HashMap<String,String>()
                                map["pid"]=m.id
                                return map

                            }
                        }

                        var queue:RequestQueue = Volley.newRequestQueue(this)
                        queue.add(stringrequest)

                    }
                )
                alert.setNegativeButton("NO",{ dialogInterface: DialogInterface, i :Int ->


                })
                alert.show()
            }
        }


        return super.onContextItemSelected(item)
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
