package com.example.projectbloodbank

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
import com.example.projectbloodbank.databinding.ActivityAddDonerBinding

class AddDonerActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAddDonerBinding

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn1:Button
    lateinit var db:DatabseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDonerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)
        edt4 = findViewById(R.id.edt4)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        btn1.setOnClickListener{

            var name = edt1.text.toString()
            var bloodgroup = edt2.text.toString()
            var contact = edt3.text.toString()
            var address = edt4.text.toString()

            var d = Donor()
            d.dname = name
            d.dbloodgroup = bloodgroup
            d.dcontact = contact
            d.daddress = address

            db.DonorDao().dinsertdata(d)
            Toast.makeText(applicationContext, " Inserted", Toast.LENGTH_SHORT).show()

            startActivity(Intent(applicationContext,MainActivity::class.java))

        }




    }
}