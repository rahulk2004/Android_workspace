package com.example.taskproducts

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class AllFragment : Fragment() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>
    lateinit var adapter: MyAdapter
    lateinit var searchView: SearchView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all, container, false)

        listView = view.findViewById(R.id.gridview)
        searchView = view.findViewById(R.id.searchView) // Add SearchView to layout
        list = ArrayList()

        // Existing code: Load products from API initially
        val stringRequest = StringRequest(
            Request.Method.GET, "https://prakrutitech.buzz/Rahul/view.php",
            object : Response.Listener<String> {
                override fun onResponse(response: String?) {
                    val jsonArray = JSONArray(response)
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)

                        val name = jsonObject.getString("pname")
                        val price = jsonObject.getString("pprice")
                        val status = jsonObject.getString("pstatus")
                        val desc = jsonObject.getString("pdesc")
                        val image = jsonObject.getString("pimage")

                        val m = Model()
                        m.name = name
                        m.price = price
                        m.status = status
                        m.desc = desc
                        m.image = image

                        list.add(m)
                    }

                    adapter = MyAdapter(activity!!, list)
                    listView.adapter = adapter
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(activity, "No Internet", Toast.LENGTH_SHORT).show()
                }
            }
        )

        val queue: RequestQueue = Volley.newRequestQueue(activity)
        queue.add(stringRequest)

        // New functionality: Add search filtering
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                loadProducts(query.orEmpty())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                loadProducts(newText.orEmpty())
                return true
            }
        })

        return view
    }

    private fun loadProducts(query: String) {
        val url = "https://prakrutitech.buzz/Rahul/search.php?query=$query"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                list.clear()
                val jsonArray = JSONArray(response)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)

                    val name = jsonObject.getString("pname")
                    val price = jsonObject.getString("pprice")
                    val status = jsonObject.getString("pstatus")
                    val desc = jsonObject.getString("pdesc")
                    val image = jsonObject.getString("pimage")

                    val m = Model()
                    m.name = name
                    m.price = price
                    m.status = status
                    m.desc = desc
                    m.image = image

                    list.add(m)
                }
                adapter.notifyDataSetChanged()
            },
            Response.ErrorListener {
                Toast.makeText(activity, "No Internet", Toast.LENGTH_SHORT).show()
            }
        )

        val queue: RequestQueue = Volley.newRequestQueue(activity)
        queue.add(stringRequest)
    }
}
