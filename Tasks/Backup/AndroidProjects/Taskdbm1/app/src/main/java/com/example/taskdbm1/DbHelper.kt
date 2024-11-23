package com.example.taskdbm1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(var context:Context) : SQLiteOpenHelper(context,DB_NAME,null,DB_VERSION){

    companion object{

        var DB_NAME = "tops.db"
        var TABLE_NAME = "details"
        var DB_VERSION = 1
        var ID = "id"
        var NAME = "name"
        var NUMBER = "number"
        var PASSOWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {

        var query = "CREATE TABLE "+ TABLE_NAME + "("+ ID +"INTEGER PRIMARY KEY, "+ NAME +" text ,"+ NUMBER +" text ,"+ PASSOWORD +" text "+")"
        db!!.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        var upquery = "DROP TABLE IF EXIST "+ TABLE_NAME
        db!!.execSQL(upquery)
        onCreate(db)
    }

    fun insertdata(m:Model):Long{
        var db :SQLiteDatabase = writableDatabase
        var values = ContentValues()

        values.put(NAME,m.name)
        values.put(NUMBER,m.num)
        values.put(PASSOWORD,m.pass)
        var id = db.insert(TABLE_NAME, ID,values)
        return id
    }

    fun viewdata():ArrayList<Model>
    {
        var list = ArrayList<Model>()
        var db = readableDatabase
        var data = arrayOf(ID,NAME, NUMBER)
        var curser = db.query(TABLE_NAME,data,null,null,null,null,null,null)

        while (curser.moveToNext())
        {
            var id = curser.getInt(0)
            var name = curser.getString(1)
            var num = curser.getString(2)

            var m = Model()
            m.id = id
            m.name = name
            m.num = num
            list.add(m)


        }

        return list

    }

    fun deletedata(id: Int) {

        var db = writableDatabase
        var deletequery = ID+"="+id
        db.delete(TABLE_NAME,deletequery,null)

    }

    fun updatedata(m:Model)
    {
        var db = writableDatabase
        var contentValues = ContentValues()
        contentValues.put(ID,m.id)
        contentValues.put(NAME,m.name)
        contentValues.put(NUMBER,m.num)
        var updatequery = ID+"="+m.id
        db.update(TABLE_NAME,contentValues,updatequery,null)

    }


}