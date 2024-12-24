package com.example.module5notes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.module5notes.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNotesBinding

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var btn1:Button
    lateinit var db:DatabaseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNotesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext,DatabaseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        if (GlobalVariables.updateFlag.equals("update"))
        {
            edt1.setText(GlobalVariables.topic)
            edt2.setText(GlobalVariables.note)
        }

        btn1.setOnClickListener{

            if (GlobalVariables.updateFlag =="update")
            {
                var topic = edt1.text.toString()
                var note = edt2.text.toString()

                var u = User()
                u.id = GlobalVariables.id
                u.topic = topic
                u.note = note

                db.daoClass().updatedata(u)

                Toast.makeText(applicationContext, "Note Updated", Toast.LENGTH_SHORT).show()

                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
            else{

                var topic = edt1.text.toString()
                var note = edt2.text.toString()

                var u = User()
                u.topic = topic
                u.note = note

                db.daoClass().insertdata(u)

                Toast.makeText(applicationContext, "Note inserted", Toast.LENGTH_SHORT).show()

                startActivity(Intent(applicationContext,MainActivity::class.java))
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()
        GlobalVariables.updateFlag = ""
    }
}