package com.example.projectbloodbank

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BloodRequestDao {

    @Insert
    fun binsertdata(bloodRequest: BloodRequest)

    @Query("select * from bloodrequest")
    fun bviewdata():MutableList<BloodRequest>

    @Query("SELECT * FROM bloodrequest WHERE user_id = :userId")
    fun fetchByUserId(userId: Int): MutableList<BloodRequest>

    @Update
    fun bupdatedata(bloodRequest: BloodRequest)

    @Delete
    fun bdeletedata(bloodRequest: BloodRequest)
}