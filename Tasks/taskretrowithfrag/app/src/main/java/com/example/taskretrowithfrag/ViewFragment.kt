package com.example.taskretrowithfrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: MutableList<Model>
    lateinit var apiinterface: Apiinterface


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_view, container, false)

        recyclerView = view.findViewById(R.id.recycle)
        list = ArrayList()

        var manager:RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = manager


        apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)

        var call : Call<List<Model>> = apiinterface.getdata()

        call.enqueue(object : Callback<List<Model>> {

            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>)
            {
                list = response.body() as MutableList<Model>

                var myAdapter = MyAdapter(requireActivity(), list as MutableList<Model>)
                recyclerView.adapter=myAdapter

            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Toast.makeText(requireActivity(),"No Internet", Toast.LENGTH_LONG).show()
            }

        })
        return view

    }


}