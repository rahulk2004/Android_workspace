import com.example.taskretrofit1.Model
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Apiinterface {

    @GET("view.php")
    fun getdata(): Call<List<Model>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insertdata(
        @Field("pname") pname: String,
        @Field("pprice") pprice: String,
        @Field("pdesc") pdesc: String,
        @Field("pstatus") pstatus: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("update.php")
    fun updatedata(
        @Field("pid") pid: String,
        @Field("pname") pname: String,
        @Field("pprice") pprice: String,
        @Field("pdesc") pdesc: String,
        @Field("pstatus") pstatus: String
    ): Call<Void>

    @FormUrlEncoded
    @POST("delete.php")
    fun deletedata(@Field("pid") pid: Int): Call<Void?>?


    @Multipart
    @POST("upload.php")
    fun uploaddata(
        @Part("pname") pname: RequestBody,
        @Part("pprice") pprice: RequestBody,
        @Part("pdesc") pdesc: RequestBody,
        @Part("pstatus") pstatus: RequestBody,
        @Part pimage: MultipartBody.Part // Image file
    ): Call<ResponseBody>

    abstract fun uploadData(namePart: RequestBody?, pricePart: RequestBody?, descPart: RequestBody?, statusPart: RequestBody?, imagePart: MultipartBody.Part?): Any
}
