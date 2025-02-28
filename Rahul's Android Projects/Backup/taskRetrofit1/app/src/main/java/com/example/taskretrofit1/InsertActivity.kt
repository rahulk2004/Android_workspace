package com.example.taskretrofit1

import Apiinterface
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class InsertActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtPrice: EditText
    private lateinit var edtDesc: EditText
    private lateinit var edtStatus: EditText
    private lateinit var btnChooseImage: Button
    private lateinit var btnInsert: Button
    private lateinit var imageView: ImageView
    private var fileUri: Uri? = null
    private lateinit var apiInterface: Apiinterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        edtName = findViewById(R.id.pname)
        edtPrice = findViewById(R.id.pprice)
        edtDesc = findViewById(R.id.pdesc)
        edtStatus = findViewById(R.id.pstatus)
        btnChooseImage = findViewById(R.id.imgbtn)
        btnInsert = findViewById(R.id.btn1)
        imageView = findViewById(R.id.img1)

        apiInterface = ApiClient.getapiclient().create(Apiinterface::class.java)

        checkPermissions()

        btnChooseImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }

        btnInsert.setOnClickListener {
            fileUri?.let { uri ->
                val file = uriToFile(uri)
                if (file != null) {
                    uploadImage(file)
                } else {
                    Toast.makeText(this, "Invalid file path", Toast.LENGTH_LONG).show()
                }
            } ?: run {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 100)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uriToFile(uri: Uri): File? {
        try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            inputStream?.let {
                val file = File(cacheDir, "upload_image.jpg")
                val outputStream = FileOutputStream(file)
                it.copyTo(outputStream)
                outputStream.close()
                it.close()
                return file
            }
        } catch (e: Exception) {
            Log.e("File Error", "Error converting URI to file: ${e.message}")
        }
        return null
    }

    private fun uploadImage(file: File) {
        val name = edtName.text.toString().trim()
        val price = edtPrice.text.toString().trim()
        val desc = edtDesc.text.toString().trim()
        val status = edtStatus.text.toString().trim()

        if (name.isEmpty() || price.isEmpty() || desc.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return
        }

        val namePart = RequestBody.create(MultipartBody.FORM, name)
        val pricePart = RequestBody.create(MultipartBody.FORM, price)
        val descPart = RequestBody.create(MultipartBody.FORM, desc)
        val statusPart = RequestBody.create(MultipartBody.FORM, status)

        val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
        val body = MultipartBody.Part.createFormData("pimage", file.name, requestFile)

        val call: Call<ResponseBody> = apiInterface.uploaddata(namePart, pricePart, descPart, statusPart, body)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Upload Success", Toast.LENGTH_LONG).show()
                    Log.d("Upload Success", response.message())
                } else {
                    Toast.makeText(applicationContext, "Upload Failed: ${response.message()}", Toast.LENGTH_LONG).show()
                    Log.e("Upload Failed", response.message())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(applicationContext, "Fail: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("Upload Failure", "Error: ${t.message}")
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            fileUri = data.data
            imageView.setImageURI(fileUri)
        }
    }
}
