package com.example.taskretrofit1

import Apiinterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var list: List<Model>
    lateinit var apiinterface: Apiinterface
    lateinit var f1: FloatingActionButton
    lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recycle)
        list = ArrayList()
        f1 = findViewById(R.id.f1)
        pieChart = findViewById(R.id.pieChart)

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager

        apiinterface = ApiClient.getapiclient().create(Apiinterface::class.java)
        val call: Call<List<Model>> = apiinterface.getdata()

        call.enqueue(object : Callback<List<Model>> {
            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                list = response.body() as MutableList<Model>
                val myAdapter = MyAdapter(applicationContext, list as MutableList<Model>)
                recyclerView.adapter = myAdapter
                setupPieChart(list)
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_LONG).show()
            }
        })

        f1.setOnClickListener {
            val i = Intent(applicationContext, InsertActivity::class.java)
            startActivity(i)
        }
    }

    private fun setupPieChart(dataList: List<Model>) {
        val pieEntries = ArrayList<PieEntry>()

        for (item in dataList) {
            pieEntries.add(PieEntry(item.pprice.toFloat(), item.pname))
        }

        val pieDataSet = PieDataSet(pieEntries, "Price Breakdown")
        pieDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        pieDataSet.valueTextSize = 14f

        val pieData = PieData(pieDataSet)

        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.setUsePercentValues(true)
        pieChart.animateY(1000)
        pieChart.invalidate()
    }
}
