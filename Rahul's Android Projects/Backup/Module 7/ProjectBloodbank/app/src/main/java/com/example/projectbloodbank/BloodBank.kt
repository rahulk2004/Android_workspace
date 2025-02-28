package com.example.projectbloodbank

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bloodbanks")
class BloodBank {

    @PrimaryKey(autoGenerate = true)
    var bid = 0

    @ColumnInfo(name = "bank_name")
    var bbankname =""

    @ColumnInfo(name = "bank_location")
    var bbanklocation =""

}