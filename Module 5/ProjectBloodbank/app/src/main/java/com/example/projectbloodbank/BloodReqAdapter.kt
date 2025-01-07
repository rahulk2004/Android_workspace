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

class BloodReqAdapter (var context: Context, var list:MutableList<BloodRequest>) : RecyclerView.Adapter<Breqview>() {

    lateinit var db:DatabseClass

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Breqview {

        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.design_breq,parent,false)

        db = Room.databaseBuilder(parent.context, DatabseClass::class.java, "myDatabase")
            .allowMainThreadQueries()
            .build()
        return Breqview(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Breqview, position: Int) {

        holder.txt1.setText(list.get(position).uname)
        holder.txt2.setText(list.get(position).bloodgroup)
        holder.txt3.setText(list.get(position).contact)
        holder.txt4.setText(list.get(position).address)
        holder.txt5.setText(list.get(position).requestdate)
        holder.txt6.setText(list.get(position).status)

        holder.itemView.setOnClickListener {

            var alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Select Operations?")
            alert.setPositiveButton("Update",{ dialogInterface: DialogInterface, i: Int ->


                GlobalVariables.bloodRequestId=list.get(position).id
                GlobalVariables.userName=list.get(position).uname
                GlobalVariables.bloodGroup=list.get(position).bloodgroup
                GlobalVariables.contact=list.get(position).contact
                GlobalVariables.address=list.get(position).address
                GlobalVariables.requestDate=list.get(position).requestdate
                GlobalVariables.status=list.get(position).status
                GlobalVariables.updateFlag = "update"

                var i = Intent(context, AddBloodReqActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context!!.startActivity(i)



            })
            alert.setNegativeButton("Delete",{ dialogInterface: DialogInterface, i: Int ->

                deleteuser(list.get(position).id)
                var i = Intent(context,BloodRequestActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)

            })
            alert.show()
        }


    }
    fun deleteuser(id:Int)
    {
        val br = BloodRequest()
        br.id =id
        db.BloodRequestDao().bdeletedata(br)
        Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show()

    }
}

class Breqview(itemview: View) : RecyclerView.ViewHolder(itemview) {

    lateinit var txt1: TextView
    lateinit var txt2: TextView
    lateinit var txt3: TextView
    lateinit var txt4: TextView
    lateinit var txt5: TextView
    lateinit var txt6: TextView

    init
    {
        txt1 = itemview.findViewById(R.id.txt1)
        txt2 = itemview.findViewById(R.id.txt2)
        txt3 = itemview.findViewById(R.id.txt3)
        txt4 = itemview.findViewById(R.id.txt4)
        txt5 = itemview.findViewById(R.id.txt5)
        txt6 = itemview.findViewById(R.id.txt6)
    }

}
