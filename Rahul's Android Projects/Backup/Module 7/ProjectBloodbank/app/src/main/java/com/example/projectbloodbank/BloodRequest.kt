package com.example.projectbloodbank

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bloodrequest")
data class BloodRequest(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "user_id")
    var uid: String = "",

    @ColumnInfo(name = "username")
    var uname: String = "",

    @ColumnInfo(name = "bloodgroup")
    var bloodgroup: String = "",

    @ColumnInfo(name = "contact")
    var contact: String = "",

    @ColumnInfo(name = "address")
    var address: String = "",

    @ColumnInfo(name = "request_date")
    var requestdate: String = "",

    @ColumnInfo(name = "status")
    var status: String = ""
)
