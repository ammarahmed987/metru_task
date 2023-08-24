package com.example.metru.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dummy")
data class DummyModel(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    @ColumnInfo(name="Name")
    var name : String,
    @ColumnInfo(name="Birthday")
    var birth : String
)