package com.example.taskretrofit1

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Apiinterface {

    @GET("view.php")
    fun getdata() : Call<List<Model>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insertdata(

        @Field("pname") pname:String,
        @Field("pprice") pprice: String,
        @Field("pdesc") pdesc:String,
        @Field("pstatus") pstatus:String,

        ): Call<Void>

    @FormUrlEncoded
    @POST("update.php")
    fun updatedata(

        @Field("pid") pid:String,
        @Field("pname") pname:String,
        @Field("pprice") pprice: String,
        @Field("pdesc") pdesc:String,
        @Field("pstatus") pstatus:String,

        ): Call<Void>

    @FormUrlEncoded
    @POST("delete.php")
    fun deletedata(@Field("pid")pid:Int):Call<Void?>?
}