package com.example.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "notesTable")
class Note(@ColumnInfo(name = "title")val notetitle:String,
           @ColumnInfo(name = "descrioption")val notediscription:String,
           @ColumnInfo(name = "timestamp")val timestamp:String,) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}