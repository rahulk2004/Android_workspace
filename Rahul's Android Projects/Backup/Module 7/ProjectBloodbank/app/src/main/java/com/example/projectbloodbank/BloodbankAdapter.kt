package com.example.projectbloodbank

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class BloodbankAdapter(var context: Context, var list:MutableList<BloodBank>) : RecyclerView.Adapter<BBview>() {

    lateinit var db:DatabseClass

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBview {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.design_bb,parent,false)

        db = Room.databaseBuilder(parent.context, DatabseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()
        return BBview(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BBview, position: Int) {

        holder.txt1.setText(list.get(position).bbankname)
        holder.txt2.setText(list.get(position).bbanklocation)

        holder.itemView.setOnClickListener {

            var alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Select Operations?")
            alert.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->


                GlobalVariables.bloodBankId=list.get(position).bid
                GlobalVariables.bloodBankName=list.get(position).bbankname
                GlobalVariables.bloodBankLocation=list.get(position).bbanklocation
                GlobalVariables.updateFlag = "update"


                var i = Intent(context, AddBloodbankActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context!!.startActivity(i)



            })
            alert.setNegativeButton("Delete",{ dialogInterface: DialogInterface, i: Int ->


                deleteuser(list.get(position).bid)
                var i = Intent(context,BloodbankActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)

            })
            alert.show()
        }
    }

    fun deleteuser(id:Int)
    {
        val bb = BloodBank()
        bb.bid = id
        db.BloodbankDao().bbdeletedata(bb)
        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()

    }



}


class BBview(itemview: View) : RecyclerView.ViewHolder(itemview) {

    lateinit var txt1: TextView
    var txt2:TextView
    init
    {
        txt1 = itemview.findViewById(R.id.txt1)
        txt2 = itemview.findViewById(R.id.txt2)
    }

}
