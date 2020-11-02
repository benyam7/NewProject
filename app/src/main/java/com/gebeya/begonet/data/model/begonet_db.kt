package com.gebeya.begonet.data.model

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = arrayOf(SignUp::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun signUpDao(): RegisterDao
}