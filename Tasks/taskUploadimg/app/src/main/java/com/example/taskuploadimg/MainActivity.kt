package com.example.taskuploadimg

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.gotev.uploadservice.MultipartUploadRequest

class MainActivity : AppCompatActivity() {

    lateinit var edt1:EditText
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var imageView: ImageView
    lateinit var filepath:Uri
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        imageView = findViewById(R.id.img1)

        permissions()

        btn1.setOnClickListener{

            var i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(i,"Select Picture"),1)
        }
        btn2.setOnClickListener{
            val name = edt1.getText().toString()
            val path = getPath(filepath)

            MultipartUploadRequest(this,"https://prakrutitech.buzz/CRUD/upload.php")
                .addFileToUpload(path,"category_image")
                .addParameter("category_name",name)
                .setMaxRetries(2)
                .startUpload()
            Toast.makeText(this@MainActivity, "sucess", Toast.LENGTH_SHORT).show()
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

        if (checkSelfPermission(READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
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