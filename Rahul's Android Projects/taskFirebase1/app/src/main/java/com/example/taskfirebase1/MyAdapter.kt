package com.example.taskfirebase1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import org.w3c.dom.Text

class MyAdapter(options: FirebaseRecyclerOptions<Model>):FirebaseRecyclerAdapter<Model,MyView>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {


        var inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.design,parent,false)

        return MyView(inflater)

    }

    override fun onBindViewHolder(holder: MyView, position: Int, model: Model) {

    }
}

class MyView(itemview:View):RecyclerView.ViewHolder(itemview) {

    var name :TextView
    var sname :TextView
    var email :TextView
    var pass :TextView


    init {

        name = itemView.findViewById(R.id.txt1)
        sname = itemView.findViewById(R.id.txt2)
        email = itemView.findViewById(R.id.txt3)
        pass = itemView.findViewById(R.id.txt4)

    }

}
