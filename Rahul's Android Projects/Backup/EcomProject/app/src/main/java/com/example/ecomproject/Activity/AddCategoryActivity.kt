package com.example.ecomproject.Activity

import android.Manifest.permission.READ_MEDIA_IMAGES
import android.app.Activity
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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ecomproject.R
import com.example.ecomproject.databinding.ActivityAddCategoryBinding
import net.gotev.uploadservice.MultipartUploadRequest

class AddCategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddCategoryBinding
    lateinit var edt1:EditText
    lateinit var btn1:Button
    lateinit var btn2:Button
    lateinit var imageview: ImageView
    lateinit var filepath:Uri
    lateinit var bitmap: Bitmap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddCategoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        edt1 = findViewById(R.id.edt1)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        imageview = findViewById(R.id.img)

        permissions()

        btn1.setOnClickListener{

            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,1)
        }

        btn2.setOnClickListener{

            filepath?.let { uri ->

                val filePath = getpath(uri)
                if (filePath != null)
                {
                    uploadImage(filePath)
                }


            }

        }

    }

    private fun getpath(uri: Uri): String? {

        val cursor:Cursor? = contentResolver.query(uri,null,null,null,null)

        cursor?.use {
            it.moveToFirst()
            val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
            if (columnIndex != -1){
                return it.getString(columnIndex)

            }
        }

        return  null

    }

    private fun uploadImage(filePath: String) {

        val name = edt1.text.toString()

        try {
            val uploadRequest = MultipartUploadRequest(this,"https://prakrutitech.buzz/AndroidAPI/addcategory.php")
                .addFileToUpload(filePath,"url")
                .addParameter("name",name)
                .setMaxRetries(2)
            
            uploadRequest.startUpload()
            Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
            
        } catch (e:Exception){
            
            e.printStackTrace()
            Log.e("Upload","Upload Failed: ${e.message}")
        }

    }

    private fun permissions() {
        if (ContextCompat.checkSelfPermission(this,READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(READ_MEDIA_IMAGES),100)
        }else{
            Toast.makeText(applicationContext, "Permission Already Granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode ==100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()

        }else{

            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode==1 && resultCode== Activity.RESULT_OK && data != null)
        {
            filepath = data.data!!
            imageview.setImageURI(filepath)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}