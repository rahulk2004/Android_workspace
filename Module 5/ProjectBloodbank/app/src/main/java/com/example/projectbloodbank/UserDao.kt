package com.example.projectbloodbank

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert
    fun uinsert(user :User)

    @Query("select * from users")
    fun uviewdata():MutableList<User>

    @Query("SELECT * FROM users WHERE user_email = :email")
    fun getUserByEmail(email: String): User?

    @Update
    fun uupdatedata(user: User)

    @Delete
    fun udeletedata(user: User)

}