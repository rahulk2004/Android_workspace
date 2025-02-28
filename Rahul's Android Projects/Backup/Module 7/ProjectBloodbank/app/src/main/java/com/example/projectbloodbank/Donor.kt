package com.example.projectbloodbank

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donors")
class Donor {

    @PrimaryKey(autoGenerate = true)
    var did = 0

    @ColumnInfo(name = "donor_name")
    var dname = ""

    @ColumnInfo(name = "donor_bloodgroup")
    var dbloodgroup = ""

    @ColumnInfo(name = "donor_contact")
    var dcontact = ""

    @ColumnInfo(name = "donor_address")
    var daddress = ""
}