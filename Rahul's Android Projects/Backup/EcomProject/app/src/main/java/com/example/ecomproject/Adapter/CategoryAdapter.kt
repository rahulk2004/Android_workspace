package com.example.ecomproject.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ecomproject.Activity.FullScreenActivity
import com.example.ecomproject.Model.CategoryModel
import com.example.ecomproject.R
import com.squareup.picasso.Picasso

class CategoryAdapter(var context: Context,var list: MutableList<CategoryModel>):RecyclerView.Adapter<MyCategoryView>(){

    private var filteredList:MutableList<CategoryModel> =list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCategoryView {
        var layoutInflater = LayoutInflater.from(parent.context)
        var view = layoutInflater.inflate(R.layout.design_categoryview,parent,false)
        return MyCategoryView(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MyCategoryView, position: Int) {

        holder.textView.text = filteredList[position].name
        holder.textView2.text = filteredList[position].description
        holder.textView3.text = filteredList[position].price
        Picasso.get().load(filteredList.get(position).url).into(holder.imageview2)

        holder.itemView.setOnClickListener{
            var intent = Intent(context,FullScreenActivity::class.java)
            intent.putExtra("image",filteredList.get(position).url)
            intent.putExtra("name",filteredList.get(position).name)
            intent.putExtra("desc",filteredList.get(position).description)
            intent.putExtra("price",filteredList.get(position).price)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }


    }

    fun updateList(filteredList:List<CategoryModel>){
        this.filteredList = filteredList.toMutableList()
        notifyDataSetChanged()
    }
}

class MyCategoryView(Itemview: View):RecyclerView.ViewHolder(Itemview) {

    var textView:TextView = Itemview.findViewById(R.id.category_txt)

    var imageview2:ImageView = Itemview.findViewById(R.id.category_img)

    var textView2:TextView =Itemview.findViewById(R.id.category_desc)

    var textView3:TextView = Itemview.findViewById(R.id.category_price)

}
