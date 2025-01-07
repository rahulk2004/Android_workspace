package com.example.projectbloodbank

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class,Donor::class,BloodRequest::class,BloodBank::class], version = 1)
abstract class DatabseClass:RoomDatabase() {

    abstract fun UserDao():UserDao

    abstract fun DonorDao():DonorDao

    abstract fun BloodRequestDao():BloodRequestDao

    abstract fun BloodbankDao():BloodbankDao



}