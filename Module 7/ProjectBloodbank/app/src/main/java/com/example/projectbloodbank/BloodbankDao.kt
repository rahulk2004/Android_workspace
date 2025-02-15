package com.example.projectbloodbank

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BloodbankDao {

    @Insert
    fun bbinsertdata(bloodBank: BloodBank)

    @Query("select * from bloodbanks")
    fun bbviewdata():MutableList<BloodBank>

    @Update
    fun bbupdatedata(bloodBank: BloodBank)

    @Delete
    fun bbdeletedata(bloodBank: BloodBank)
}