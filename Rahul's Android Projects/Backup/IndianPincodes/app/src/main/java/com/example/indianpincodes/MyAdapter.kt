package com.example.indianpincodes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase

class MyAdapter(
    options: FirebaseRecyclerOptions<Model>
) : FirebaseRecyclerAdapter<Model, MyAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.design, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Model) {
        val databaseRef = FirebaseDatabase.getInstance().reference.child("favorites")

        holder.bind(model)


        databaseRef.child(model.pincode).get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                holder.likeButton.setImageResource(R.drawable.ic_favorite)
            } else {
                holder.likeButton.setImageResource(R.drawable.ic_unliked)
            }
        }

        holder.likeButton.setOnClickListener {
            databaseRef.child(model.pincode).get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    databaseRef.child(model.pincode).removeValue()
                    holder.likeButton.setImageResource(R.drawable.ic_unliked)
                } else {
                    databaseRef.child(model.pincode).setValue(model)
                    holder.likeButton.setImageResource(R.drawable.ic_favorite)
                }
            }
        }


        holder.shareButton.setOnClickListener {
            val shareText = """Pincode: ${model.pincode}
                |Office Name: ${model.officename}
                |District: ${model.district}
                |State: ${model.state}
                |Office Type: ${model.officetype}
                |Delivery Status: ${model.deliverystatus}
                |Taluka: ${model.taluka}
                |Telephone: ${model.telephone}
                |Head Office: ${model.headoffice}
                |Division: ${model.division}
                |Region: ${model.region}
                |Circle: ${model.circle}""".trimMargin()

            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, shareText)
                type = "text/plain"
            }
            holder.itemView.context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pincode: TextView = itemView.findViewById(R.id.txt1)
        val officename: TextView = itemView.findViewById(R.id.txt2)
        val district: TextView = itemView.findViewById(R.id.txt3)
        val state: TextView = itemView.findViewById(R.id.txt4)
        val officetype: TextView = itemView.findViewById(R.id.txt5)
        val deliverystatus: TextView = itemView.findViewById(R.id.txt6)
        val taluka: TextView = itemView.findViewById(R.id.txt7)
        val telephone: TextView = itemView.findViewById(R.id.txt8)
        val headoffice: TextView = itemView.findViewById(R.id.txt9)
        val division: TextView = itemView.findViewById(R.id.txt10)
        val region: TextView = itemView.findViewById(R.id.txt11)
        val circle: TextView = itemView.findViewById(R.id.txt12)
        val likeButton: ImageView = itemView.findViewById(R.id.imgedit)
        val shareButton: ImageView = itemView.findViewById(R.id.imgdelete)

        fun bind(model: Model) {
            pincode.text = model.pincode
            officename.text = model.officename
            district.text = model.district
            state.text = model.state
            officetype.text = model.officetype
            deliverystatus.text = model.deliverystatus
            taluka.text = model.taluka
            telephone.text = model.telephone
            headoffice.text = model.headoffice
            division.text = model.division
            region.text = model.region
            circle.text = model.circle
        }
    }
}
