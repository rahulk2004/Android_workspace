package com.example.taskretrofit2

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{

        lateinit var retrofit: Retrofit

        fun getapiclient():Retrofit

        {
            retrofit = Retrofit.Builder()
                .baseUrl("https://prakrutitech.buzz/Rahul/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }


    }
}