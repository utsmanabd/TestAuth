package com.everybodv.testauth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction")
data class Transaction(
    @field:ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:ColumnInfo(name = "idUser")
    val idUser: Int,

    @field:ColumnInfo(name = "date")
    val date: String,

    @field:ColumnInfo(name = "time")
    val time: String,

    @field:ColumnInfo(name = "numItem")
    val numItem: Int,

    @field:ColumnInfo(name = "total")
    val total: Int
)