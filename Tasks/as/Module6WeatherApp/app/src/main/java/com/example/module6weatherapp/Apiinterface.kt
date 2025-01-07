package com.example.module6weatherapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Apiinterface {

    @GET("data/2.5/weather")
    fun getdata(
        @Query("q") cityName:String ,
        @Query("appid") apiKey:String ,
        @Query("units") units:String = "metric"
    ):Call<Model>
}