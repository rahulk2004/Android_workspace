package com.example.ecomproject.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.Activity.CartActivity
import com.example.ecomproject.Activity.PaymentActivity
import com.example.ecomproject.Model.CartModel
import com.example.ecomproject.R
import com.example.ecomproject.clients.ApiClient
import com.example.ecomproject.interfaces.ApiInterface
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartAdapter(var context: Context?,var mutableList: MutableList<CartModel>):RecyclerView.Adapter<CartViewHolder>() {

    lateinit var apiInterface: ApiInterface
    lateinit var sharedPreferences: SharedPreferences

    companion object{

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cart_adapter_layout,parent,false
        )
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, @SuppressLint("RecyclerView") position: Int) {

        sharedPreferences = context!!.getSharedPreferences("user_session",Context.MODE_PRIVATE)
        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
        val myId = mutableList[position].id
        val giftName = mutableList[position].gift_name
        val giftPrice = mutableList[position].gift_price
        val giftDesc = mutableList[position].gift_description
        val giftImage = mutableList[position].image

        holder.textView1.text = giftName
        holder.textView2.text = giftPrice
        holder.textView3.text = giftDesc
        Picasso.get().load(giftImage).into(holder.imageView)

        holder.removeFromCartBtn.setOnClickListener{
            val call = apiInterface.deleteCart(myId)

            call.enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    mutableList.removeAt(position)

                    Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show()
                    var i  = Intent(context,CartActivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context!!.startActivity(i)

                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }

            })

        }

        holder.makePayment.setOnClickListener{

            val intent = Intent(context,PaymentActivity::class.java)
            intent.putExtra("id",mutableList[position].id)
            intent.putExtra("pName",mutableList[position].gift_name)
            intent.putExtra("pPrice",mutableList[position].gift_price)
            intent.putExtra("pDesc",mutableList[position].gift_description)
            intent.putExtra("pImage",mutableList[position].image)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)


        }
    }
}

class CartViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    val imageView:ImageView
    val textView1:MaterialTextView
    val textView2:MaterialTextView
    val textView3:MaterialTextView

    val removeFromCartBtn:MaterialButton
    val makePayment:MaterialButton

    init {
        imageView = itemView.findViewById(R.id.imageView)
        textView1 = itemView.findViewById(R.id.tvName)
        textView2 = itemView.findViewById(R.id.tvPrice)
        textView3 = itemView.findViewById(R.id.tvDesc)
        removeFromCartBtn = itemView.findViewById(R.id.removeFromCartBtn)
        makePayment = itemView.findViewById(R.id.makePayment)
    }

}
