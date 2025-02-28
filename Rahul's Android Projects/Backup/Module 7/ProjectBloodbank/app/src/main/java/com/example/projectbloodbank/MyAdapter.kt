package com.example.projectbloodbank

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.Image
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.orhanobut.dialogplus.DialogPlus

class MyAdapter(var context: Context,var list: MutableList<Donor>):RecyclerView.Adapter<Myview>() {

    lateinit var db :DatabseClass
    private var filteredList: MutableList<Donor> = ArrayList(list)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myview {

        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.design,parent,false)

        db = Room.databaseBuilder(parent.context,DatabseClass::class.java,"myDatabase")
            .allowMainThreadQueries()
            .build()

        return Myview(view)

    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: Myview, position: Int) {
        val donor = filteredList[position]
        holder.txt1.text = donor.dname
        holder.txt2.text = donor.dbloodgroup
        holder.txt3.text = donor.dcontact
        holder.txt4.text = donor.daddress

        holder.img1.setOnClickListener{

            val dialogPlus = DialogPlus.newDialog(holder.img1.context)
                .setContentHolder(com.orhanobut.dialogplus.ViewHolder(R.layout.dialogcontent))
                .setContentBackgroundResource(android.R.color.transparent)
                .setExpanded(false)
                .setGravity(Gravity.CENTER)
                .setMargin(50, 0, 50, 0)
                .create()

            val myview =dialogPlus.holderView
            val name = myview.findViewById<EditText>(R.id.uname)
            val blood = myview.findViewById<EditText>(R.id.ubloodgroup)
            val contact = myview.findViewById<EditText>(R.id.ucontact)
            val address = myview.findViewById<EditText>(R.id.uaddress)
            val submit = myview.findViewById<Button>(R.id.usubmit)

            name.setText(list.get(position).dname)
            blood.setText(list.get(position).dbloodgroup)
            contact.setText(list.get(position).dcontact)
            address.setText(list.get(position).daddress)


            submit.setOnClickListener{

                val updatedName = name.text.toString()
                val updatedBloodGroup = blood.text.toString()
                val updatedContact = contact.text.toString()
                val updatedAddress = address.text.toString()

                var d = Donor()
                d.did = list.get(position).did
                d.dname = updatedName
                d.dbloodgroup = updatedBloodGroup
                d.dcontact = updatedContact
                d.daddress = updatedAddress

                db.DonorDao().dupdatedata(d)

                notifyItemChanged(position)
                dialogPlus.dismiss()

                Toast.makeText(context,"Updated", Toast.LENGTH_LONG).show()


            }
            dialogPlus.show()



        }

        holder.img2.setOnClickListener{

            var alert = AlertDialog.Builder(holder.itemView.context)
            alert.setTitle("Are You Sure you want delete ?")
            alert.setPositiveButton("Delete",{
                dialogInterface:DialogInterface,i :Int ->

                deleteuser(list.get(position).did)
                var i = Intent(context,DonorActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(i)

            })
            alert.setNegativeButton("No",{
                dialogInterface:DialogInterface,i:Int ->


            })
            alert.show()


        }

    }

     fun deleteuser(did: Int) {

         val donor = Donor()
         donor.did = did
         db!!.DonorDao().ddeletedata(donor)

         Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()

    }

    fun filterList(query: String) {
        filteredList = if (query.isEmpty()) {
            ArrayList(list) // If query is empty, reset to the original list
        } else {
            list.filter {
                it.dname.contains(query, ignoreCase = true) ||  // Search by name
                        it.dbloodgroup.contains(query, ignoreCase = true) // Search by blood group
            }.toMutableList()
        }
        notifyDataSetChanged()
    }


}

class Myview(itemview: View):RecyclerView.ViewHolder(itemview) {

   lateinit var txt1 :TextView
   lateinit var txt2 :TextView
   lateinit var txt3 :TextView
   lateinit var txt4 :TextView
   lateinit var img1 :ImageView
   lateinit var img2 :ImageView

    init {
        txt1 = itemview.findViewById(R.id.txt1)
        txt2 = itemview.findViewById(R.id.txt2)
        txt3 = itemview.findViewById(R.id.txt3)
        txt4 = itemview.findViewById(R.id.txt4)
        img1 = itemview.findViewById(R.id.imgupdate)
        img2 = itemview.findViewById(R.id.imgdelete)
    }

}
