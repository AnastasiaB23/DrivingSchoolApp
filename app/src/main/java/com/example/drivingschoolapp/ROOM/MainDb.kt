package com.example.drivingschoolapp.ROOM

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class MainDb : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        private const val DATABASE_NAME = "main_db"

        fun getDb(context: Context): MainDb {
            return Room.databaseBuilder(
                context.applicationContext, MainDb::class.java, DATABASE_NAME
            ).build()
        }
    }
}
