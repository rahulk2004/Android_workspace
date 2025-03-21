package com.example.projectex1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.classproject.clients.ApiClient
import com.example.classproject.interfaces.ApiInterface
import com.example.projectex1.Activity.CartAcivity
import com.example.projectex1.Activity.PaymentActivity
import com.example.projectex1.Activity.WishlistActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartAdapter(var context: Context?, var mutableList: MutableList<CartModel>) :
    RecyclerView.Adapter<CartViewHolder>() {

    lateinit var apiInterface: ApiInterface
    lateinit var sharedPreferences: SharedPreferences

    companion object {
       // lateinit var itemClickListener: //ItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cart_adapter_layout, parent, false
        )
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mutableList.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, @SuppressLint("RecyclerView") position: Int) {

        sharedPreferences = context!!
            .getSharedPreferences("user_session", Context.MODE_PRIVATE)
        apiInterface = ApiClient.getapiclient()!!.create(ApiInterface::class.java)
        val myId = mutableList[position].id
        val giftName = mutableList[position].gift_name
        val giftPrice = mutableList[position].gift_price
        val giftDesc = mutableList[position].gift_description
        val giftImage = mutableList[position].image

        holder.textView1.text = giftName
        holder.textView2.text = giftPrice
        Picasso.get().load(giftImage).into(holder.imageView)

//        holder.itemView.setOnClickListener {
//            var intent = Intent(context, FullScreenActivity::class.java)
//            intent.putExtra("image", giftImage)
//            intent.putExtra("name", giftName)
//            intent.putExtra("desc", giftDesc)
//            intent.putExtra("price", giftPrice)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            context!!.startActivity(intent)
//        }

        holder.removeFromCartBtn.setOnClickListener {
            val call = apiInterface.deleteCart(myId)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    mutableList.removeAt(position)
                    //itemClickListener.onItemClick(holder.adapterPosition, it)
                    Toast.makeText(context, "Removed from cart", Toast.LENGTH_SHORT).show()
                    var i = Intent(context, CartAcivity::class.java)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context!!.startActivity(i)
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }

        holder.makePayment.setOnClickListener {

            val intent = Intent(context, PaymentActivity::class.java)
            intent.putExtra("id",mutableList[position].id)
            intent.putExtra("pName", mutableList[position].gift_name)
            intent.putExtra("pPrice", mutableList[position].gift_price)
            intent.putExtra("pDesc", mutableList[position].gift_description)
            intent.putExtra("pImage", mutableList[position].image)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context!!.startActivity(intent)

        }

//        holder.addToCartBtn.setOnClickListener {
//
//            val mob = sharedPreferences.getString("mob", "")
//
//            val call = apiInterface.addDataToCart(
//                giftName, giftDesc,
//                giftPrice, giftImage, mob
//            )
//            call.enqueue(object : Callback<Void> {
//                override fun onResponse(call: Call<Void>, response: Response<Void>) {
//                    Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT)
//                        .show()
//                }
//
//                override fun onFailure(call: Call<Void>, t: Throwable) {
//                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//                }
//            })
//        }
    }

//    interface ItemClickListener {
//        fun onItemClick(position: Int, view: View)
//    }
//
//    fun setOnItemClickListener(myClickListener: ItemClickListener) {
//        itemClickListener = myClickListener
//    }
}

class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView
    val textView1: MaterialTextView
    val textView2: MaterialTextView
    val removeFromCartBtn: MaterialButton
    val makePayment:MaterialButton

    init {
        imageView = itemView.findViewById(R.id.imageView)
        textView1 = itemView.findViewById(R.id.tvName)
        textView2 = itemView.findViewById(R.id.tvPrice)
        removeFromCartBtn = itemView.findViewById(R.id.removeFromCartBtn)
        makePayment = itemView.findViewById(R.id.makePayment)
    }
}