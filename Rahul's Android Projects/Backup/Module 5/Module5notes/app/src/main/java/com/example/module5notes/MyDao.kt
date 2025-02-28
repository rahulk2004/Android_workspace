package com.example.module5notes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface MyDao {

    @Insert
    fun insertdata(notes: User)

    @Query("select * from notes")
    fun viewdata():MutableList<User>

    @Update
    fun updatedata(notes: User)

    @Delete
    fun deletedata(notes: User)
}