package com.example.taskslider

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderLayout.Transformer.*
import com.daimajia.slider.library.SliderTypes.TextSliderView

class MainActivity : AppCompatActivity() {

    lateinit var sliderLayout: SliderLayout
    var map = HashMap<String,Int>()

    lateinit var btn1:Button
    lateinit var btn2:Button

    lateinit var auto:AutoCompleteTextView
    var city = arrayOf("rajkot","ahemadabad","Baroda")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sliderLayout = findViewById(R.id.slider)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)

        auto = findViewById(R.id.auto1)
        auto.threshold=1

        var adapter = ArrayAdapter(applicationContext,android.R.layout.select_dialog_item,city)
        auto.setAdapter(adapter)


       map.put("Pizza",R.drawable.p1img)
        map.put("Burger",R.drawable.p2img)
        map.put("Chips",R.drawable.p3img)

        for(i in map.keys){

            var txtSlider =TextSliderView(this)
            txtSlider.image(map.get(i)!!)

            sliderLayout.addSlider(txtSlider)
        }

        sliderLayout.setPresetTransformer(FlipPage)


        btn1.setOnClickListener{

            var d = DateFragment()
            d.show(supportFragmentManager,"")

        }

        btn2.setOnClickListener{

            var d = TimeFragmnet()
            d.show(supportFragmentManager,"")
        }






    }
}