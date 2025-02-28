package com.example.taskpadmin

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import net.gotev.uploadservice.MultipartUploadRequest

class UpdateProductActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var edt2:EditText
    lateinit var edt3:EditText
    lateinit var edt4:EditText
    lateinit var btn1:Button

    lateinit var ibtn:Button
    lateinit var imageView: ImageView
    lateinit var filepath: Uri
    lateinit var bitmap: Bitmap

    @SuppressLint("SuspiciousIndentation", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.uname)
        edt2 = findViewById(R.id.uprice)
        edt3 = findViewById(R.id.udesc)
        edt4 = findViewById(R.id.ustatus)
        btn1 = findViewById(R.id.ubtn)

        ibtn = findViewById(R.id.btn2)
        imageView = findViewById(R.id.uimg)

        permissions()

        var i = intent
        var id = i.getStringExtra("pid")
        edt1.setText(i.getStringExtra("pname"))
        edt2.setText(i.getStringExtra("pprice"))
        edt3.setText(i.getStringExtra("pdesc"))
        edt4.setText(i.getStringExtra("pstatus"))


        ibtn.setOnClickListener{

            var i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"Select Picture"),1)
        }

        btn1.setOnClickListener{

            var n = edt1.text.toString()
            var p = edt2.text.toString()
            var d = edt3.text.toString()
            var s = edt4.text.toString()



            val path = getPath(filepath)



            MultipartUploadRequest(this, "https://prakrutitech.buzz/Rahul/update.php")
                .addFileToUpload(path, "pimage")
                .addParameter("pid",id)
                .addParameter("pname",n)
                .addParameter("pprice",p)
                .addParameter("pdesc",d)
                .addParameter("pstatus",s)
                .setMaxRetries(2)
                .startUpload()

            Toast.makeText(this@UpdateProductActivity, "Success", Toast.LENGTH_SHORT).show()



        }

    }

    private fun getPath(uri: Uri): String? {

        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1) {
                return it.getString(columnIndex)
            }
        }
        return null

    }

    private fun permissions() {


        if (checkSelfPermission(READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE),100)
        }
        else{
            Toast.makeText(applicationContext, "Permission Alredy Granted", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1&&resultCode == RESULT_OK && data != null)
        {
            filepath = data.data!!
            bitmap = MediaStore.Images.Media.getBitmap(contentResolver,filepath)
            imageView.setImageBitmap(bitmap)

        }


        super.onActivityResult(requestCode, resultCode, data)
    }
}