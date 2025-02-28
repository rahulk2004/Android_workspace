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
import com.example.projectbloodbank.databinding.ActivityAddBloodbankBinding
import com.example.projectbloodbank.databinding.ActivityAddDonerBinding

class AddBloodbankActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBloodbankBinding

    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var btn1: Button
    lateinit var db:DatabseClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBloodbankBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        btn1 = findViewById(R.id.btn1)

        db = Room.databaseBuilder(applicationContext,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        if (GlobalVariables.updateFlag.equals("update"))
        {
            edt1.setText(GlobalVariables.bloodBankName)
            edt2.setText(GlobalVariables.bloodBankLocation)

        }

        btn1.setOnClickListener {

            if (GlobalVariables.updateFlag == "update") {

                var name = edt1.text.toString()
                var location = edt2.text.toString()

                var bb = BloodBank()
                bb.bid = GlobalVariables.bloodBankId
                bb.bbankname = name
                bb.bbanklocation = location

                db.BloodbankDao().bbupdatedata(bb)
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {

                var bname = edt1.text.toString()
                var blocation = edt2.text.toString()

                var bb = BloodBank()
                bb.bbankname = bname
                bb.bbanklocation = blocation

                db.BloodbankDao().bbinsertdata(bb)
                Toast.makeText(applicationContext, " Inserted", Toast.LENGTH_SHORT).show()

                startActivity(Intent(applicationContext, BloodbankActivity::class.java))

            }

        }


    }
}