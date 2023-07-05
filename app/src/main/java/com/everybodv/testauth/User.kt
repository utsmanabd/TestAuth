package com.everybodv.testauth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @field:ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @field:ColumnInfo(name = "username")
    val username: String,

    @field:ColumnInfo(name = "password")
    val password: String,

    @field:ColumnInfo(name = "role")
    val role: String = "user"

)