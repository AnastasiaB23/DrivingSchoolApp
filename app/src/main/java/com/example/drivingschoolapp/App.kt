package com.example.drivingschoolapp

import android.app.Application
import com.example.drivingschoolapp.ROOM.MainDb
import com.example.drivingschoolapp.ROOM.UserRepositoryImpl

class App : Application() {
    val db by lazy { MainDb.getDb(this) }
    val userRepository by lazy { UserRepositoryImpl(db.userDao) }
}