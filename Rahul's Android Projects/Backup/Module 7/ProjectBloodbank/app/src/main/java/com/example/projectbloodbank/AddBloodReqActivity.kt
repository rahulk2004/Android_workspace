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
import com.example.projectbloodbank.databinding.ActivityAddBloodReqBinding
import com.example.projectbloodbank.databinding.ActivityAddBloodbankBinding

class AddBloodReqActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBloodReqBinding

    lateinit var edt1: EditText
    lateinit var edt2: EditText
    lateinit var edt3: EditText
    lateinit var edt4: EditText
    lateinit var edt5: EditText
    lateinit var edt6: EditText
    lateinit var btn1: Button
    lateinit var db:DatabseClass
    private var loggedInUserId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBloodReqBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        edt2 = findViewById(R.id.edt2)
        edt3 = findViewById(R.id.edt3)
        edt4 = findViewById(R.id.edt4)
        edt5 = findViewById(R.id.edt5)
        edt6 = findViewById(R.id.edt6)
        btn1 = findViewById(R.id.btnSubmit)

        db = Room.databaseBuilder(applicationContext,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        val sharedPreferences = getSharedPreferences("BloodBankPrefs", MODE_PRIVATE)
        loggedInUserId = sharedPreferences.getInt("userId", -1)

        if (GlobalVariables.updateFlag.equals("update"))
        {
            edt1.setText(GlobalVariables.userName)
            edt2.setText(GlobalVariables.bloodGroup)
            edt3.setText(GlobalVariables.contact)
            edt4.setText(GlobalVariables.address)
            edt5.setText(GlobalVariables.requestDate)
            edt6.setText(GlobalVariables.status)

        }

        btn1.setOnClickListener {

            if (GlobalVariables.updateFlag == "update") {

                var bname = edt1.text.toString()
                var bblood= edt2.text.toString()
                var bcontact = edt3.text.toString()
                var baddress = edt4.text.toString()
                var breqdate = edt5.text.toString()
                var bstatus = edt6.text.toString()

                var br = BloodRequest()
                br.uid = loggedInUserId.toString()
                br.uname = bname
                br.bloodgroup = bblood
                br.contact = bcontact
                br.address = baddress
                br.requestdate = breqdate
                br.status = bstatus

                db.BloodRequestDao().bupdatedata(br)
                Toast.makeText(applicationContext, "Updated", Toast.LENGTH_LONG).show()
                startActivity(Intent(applicationContext, BloodRequestActivity::class.java))
            } else {

//                var bid = edt1.text.toString()
                var bname = edt1.text.toString()
                var bblood= edt2.text.toString()
                var bcontact = edt3.text.toString()
                var baddress = edt4.text.toString()
                var breqdate = edt5.text.toString()
                var bstatus = edt6.text.toString()

                var br = BloodRequest()
                br.uid = loggedInUserId.toString()
                br.uname = bname
                br.bloodgroup = bblood
                br.contact = bcontact
                br.address = baddress
                br.requestdate = breqdate
                br.status = bstatus

                db.BloodRequestDao().binsertdata(br)
                Toast.makeText(applicationContext, " Inserted", Toast.LENGTH_SHORT).show()

                startActivity(Intent(applicationContext, BloodRequestActivity::class.java))

            }

        }


    }
}