package com.example.drivingschoolapp.ROOM

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "surname") var surname: String,
    @ColumnInfo(name = "patronymic") var patronymic: String,
    @ColumnInfo(name = "phoneNumber") var phoneNumber: String,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "image") var profile_image: String
)
