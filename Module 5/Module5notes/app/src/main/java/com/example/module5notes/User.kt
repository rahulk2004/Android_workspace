package com.example.module5notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class User {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "topic")
    var topic = ""

    @ColumnInfo(name = "note")
    var note = ""
}