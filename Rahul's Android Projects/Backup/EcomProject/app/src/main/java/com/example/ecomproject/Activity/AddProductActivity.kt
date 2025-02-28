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
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ecomproject.Model.data2
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.databinding.ActivityAddProductBinding
import com.example.ecomproject.interfaces.ApiInterface
import net.gotev.uploadservice.MultipartUploadRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener{


        private lateinit var binding: ActivityAddProductBinding
        lateinit var filepath :Uri
        lateinit var bitmap: Bitmap
        lateinit var apiInterface: ApiInterface
        lateinit var list: MutableList<data2>

        var categoryNames = arrayOf("Select Cateory")
        var categoryid = arrayOf(0)
        var finalcid = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            binding = ActivityAddProductBinding.inflate(layoutInflater)
            val view = binding.root

            setContentView(view)

            permission()

            apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
            list = ArrayList()

            var call :Call<List<data2>> = apiInterface.dashboard_viewdata2()

            call.enqueue(object :Callback<List<data2>>{
                override fun onResponse(call: Call<List<data2>>, response: Response<List<data2>>) {

                    if (response.isSuccessful){
                        list = response.body() as MutableList<data2>

                        categoryNames = list.map { it.name }.toTypedArray()
                        categoryid = list.map { it.id }.toTypedArray()

                        val adapter = ArrayAdapter(

                            applicationContext,
                            android.R.layout.simple_spinner_item,
                            categoryNames
                        )

                        var adapter2 = ArrayAdapter(

                            applicationContext,
                            android.R.layout.simple_spinner_item,
                            categoryid
                        )

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                        binding.spin.adapter = adapter
                        binding.spin2.adapter = adapter2

                    }
                }

                override fun onFailure(call: Call<List<data2>>, t: Throwable) {

                    Toast.makeText(applicationContext, "No Internet", Toast.LENGTH_SHORT).show()
                }

            })

            binding.spin.setOnItemSelectedListener(this)
            binding.spin2.setOnItemSelectedListener(this)

            binding.img.setOnClickListener{

                val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,1)


            }
            binding.btn1.setOnClickListener{

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

            val cursor : Cursor? = contentResolver.query(uri,null,null,null,null)
            cursor?.use {

                it.moveToFirst()
                val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)

                if (columnIndex != -1){
                    return it.getString(columnIndex)
                }

            }

            return null

        }

        private fun uploadImage(filePath: String) {
            var name = binding.edtname.text.toString()
            var price = binding.edtprice.text.toString()
            var des = binding.edtdes.text.toString()

            try {
                val uploadRequest = MultipartUploadRequest(this,"https://prakrutitech.buzz/AndroidAPI/addproduct.php")
                    .addFileToUpload(filePath,"url")
                    .addParameter("name",name)
                    .addParameter("description",des)
                    .addParameter("price",price)
                    .addParameter("c_id",finalcid.toString())
                    .setMaxRetries(2)

                uploadRequest.startUpload()
                Toast.makeText(applicationContext, "Success", Toast.LENGTH_SHORT).show()

            }catch (e:Exception){
                e.printStackTrace()
                Log.e("Upload","Upload failed:${e.message}")
            }



        }

        private fun permission() {

            if (ContextCompat.checkSelfPermission(
                    this,
                    READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED)
            {
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

            if (requestCode == 100 && grantResults.isNotEmpty()&& grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

            if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null){

                filepath = data.data!!
                binding.img.setImageURI(filepath)
            }


            super.onActivityResult(requestCode, resultCode, data)
        }


        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            var pos = position

            binding.spin2.setSelection(pos)

            if (binding.spin2 != null){

                binding.spin2.onItemSelectedListener = object  :AdapterView.OnItemSelectedListener{

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(applicationContext, ""+categoryid[position], Toast.LENGTH_SHORT).show()
                        finalcid = categoryid[position]
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }

            }


        }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }




}



