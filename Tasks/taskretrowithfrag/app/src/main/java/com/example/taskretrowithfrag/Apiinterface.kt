package com.example.taskretrowithfrag

import retrofit2.Call
import retrofit2.http.GET

interface Apiinterface {

    @GET("userview.php")
    fun getdata() : Call<List<Model>>
}