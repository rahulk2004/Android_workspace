package com.example.projectbloodbank

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DonorDao {

    @Insert
    fun dinsertdata(donor: Donor)

    @Query("select * from donors")
    fun dviewdata():MutableList<Donor>

    @Update
    fun dupdatedata(donor: Donor)

    @Delete
    fun ddeletedata(donor: Donor)
}