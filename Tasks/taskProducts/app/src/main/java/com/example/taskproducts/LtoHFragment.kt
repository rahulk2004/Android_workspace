package com.example.taskproducts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


class LtoHFragment : Fragment() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_lto_h, container, false)

        listView = view.findViewById(R.id.gridview)
        list = ArrayList()


        var stringRequest = StringRequest(
            Request.Method.GET,"https://prakrutitech.buzz/Rahul/filterasc.php",
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

                    var myadapter = MyAdapter(activity!!,list)
                    listView.adapter = myadapter
                }
            },
            object : Response.ErrorListener
            {
                override fun onErrorResponse(error: VolleyError?) {
                    Toast.makeText(activity, "No Internet", Toast.LENGTH_SHORT).show()
                }

            }

        )

        var queue: RequestQueue = Volley.newRequestQueue(activity)
        queue.add(stringRequest)






        return  view
    }

}