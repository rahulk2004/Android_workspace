package com.example.tasklist

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Objects

private fun SearchView.setOnQueryTextListener(onQueryTextListener: SearchView.OnQueryTextListener, function: () -> Unit) {

}

class MainActivity : AppCompatActivity() , TextToSpeech.OnInitListener {

    lateinit var listview: ListView
    lateinit var list: MutableList<String>
    lateinit var editText: EditText
    lateinit var btn: Button
    lateinit var adapter: ArrayAdapter<String>
    lateinit var searchView: SearchView
    lateinit var srcbtn:Button
    lateinit var tts:TextToSpeech



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listview=findViewById(R.id.list)
        list=ArrayList()
        editText=findViewById(R.id.edt1)
        btn=findViewById(R.id.btn1)
        searchView=findViewById(R.id.src1)
        srcbtn = findViewById(R.id.srcbtn)


        var adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)

        listview.adapter=adapter
        btn.setOnClickListener{
            Toast.makeText(applicationContext, "1", Toast.LENGTH_SHORT).show()

            var data = editText.text.toString()
            list.add(data)
            adapter.notifyDataSetChanged()


        }

        srcbtn.setOnClickListener{

            speak()
        }


        list.add("android")
        list.add("java")
        list.add("kotlin")
        list.add("php")
        list.add("html")


        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {

                var finalvalue = p0!!.trim()

                if (list.contains(finalvalue)) {

                    adapter.filter.filter(p0)

                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {

                adapter.filter.filter(p0)

                return false
            }


        })

    }

    private fun speak() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE)

        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"SPEAK NOW")
        startActivityForResult(intent,99)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 99 && resultCode == RESULT_OK) {
            val results = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!results.isNullOrEmpty()) {
                searchView?.setQuery(results[0], false)
            }
        }


        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onInit(p0: Int) {

        tts.setPitch(0.9F)
        tts.setSpeechRate(0.5F)

    }
}