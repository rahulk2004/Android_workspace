package com.example.taskabcdgridview

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.GridView
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var listView: GridView
    lateinit var list: MutableList<Model>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        listView=findViewById(R.id.gridview)
        list=ArrayList()

        list.add(Model(R.drawable.a,"Apple"))
        list.add(Model(R.drawable.b,"Ball"))
        list.add(Model(R.drawable.c,"Cat"))
        list.add(Model(R.drawable.d,"Dog"))
        list.add(Model(R.drawable.e,"Elephant"))
        list.add(Model(R.drawable.f,"Fox"))
        list.add(Model(R.drawable.g,"Gun"))
        list.add(Model(R.drawable.h,"hat"))
        list.add(Model(R.drawable.i,"ice cream"))
        list.add(Model(R.drawable.j,"jug"))
        list.add(Model(R.drawable.k,"kite"))
        list.add(Model(R.drawable.l,"Lock"))
        list.add(Model(R.drawable.m,"Monkey"))
        list.add(Model(R.drawable.n,"N"))
        list.add(Model(R.drawable.o,"O"))
        list.add(Model(R.drawable.p,"P"))
        list.add(Model(R.drawable.q,"Q"))
        list.add(Model(R.drawable.r,"R"))
        list.add(Model(R.drawable.s,"S"))
        list.add(Model(R.drawable.t,"T"))
        list.add(Model(R.drawable.u,"U"))
//        list.add(Model(R.drawable.v,"Nose"))
//        list.add(Model(R.drawable.w,"Nose"))
//        list.add(Model(R.drawable.x,"Nose"))
        list.add(Model(R.drawable.y,"Y"))
        list.add(Model(R.drawable.z,"Z"))



        var adapter=MyAdapter(applicationContext,list)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

            if(position==0){


                var mp = MediaPlayer.create(applicationContext,R.raw.a,)
                mp.start()
                Toast.makeText(applicationContext, "A Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==1){


                var mp = MediaPlayer.create(applicationContext,R.raw.b,)
                mp.start()
                Toast.makeText(applicationContext, "B Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==2){


                var mp = MediaPlayer.create(applicationContext,R.raw.c,)
                mp.start()
                Toast.makeText(applicationContext, "C Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==3){


                var mp = MediaPlayer.create(applicationContext,R.raw.d,)
                mp.start()
                Toast.makeText(applicationContext, "D Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==4){


                var mp = MediaPlayer.create(applicationContext,R.raw.e,)
                mp.start()
                Toast.makeText(applicationContext, "E Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==5){


                var mp = MediaPlayer.create(applicationContext,R.raw.f,)
                mp.start()
                Toast.makeText(applicationContext, "F Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==6){


                var mp = MediaPlayer.create(applicationContext,R.raw.g,)
                mp.start()
                Toast.makeText(applicationContext, "G Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==7){


                var mp = MediaPlayer.create(applicationContext,R.raw.h,)
                mp.start()
                Toast.makeText(applicationContext, "H Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==8){


                var mp = MediaPlayer.create(applicationContext,R.raw.i,)
                mp.start()
                Toast.makeText(applicationContext, "I Played ", Toast.LENGTH_SHORT).show()

            }

            if(position==9){


                var mp = MediaPlayer.create(applicationContext,R.raw.j,)
                mp.start()
                Toast.makeText(applicationContext, "J Played ", Toast.LENGTH_SHORT).show()

            }




        }
    }
}