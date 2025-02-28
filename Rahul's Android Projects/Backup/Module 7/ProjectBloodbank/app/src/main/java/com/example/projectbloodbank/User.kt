package com.example.projectbloodbank

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User {

    @PrimaryKey(autoGenerate = true)
    var uid = 0

    @ColumnInfo(name = "username")
    var uname = ""

    @ColumnInfo(name = "user_email")
    var uemail = ""

    @ColumnInfo(name = "user_password")
    var upass = ""

    @ColumnInfo(name = "user_type")
    var utype: String = "user"


}