package com.gebeya.begonet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gebeya.begonet.data.model.LogIn
import com.gebeya.begonet.data.model.LoginDao
import com.gebeya.begonet.data.model.SignUp
import com.gebeya.begonet.data.model.RegisterDao

@Database(entities = [SignUp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun signUpDao() : RegisterDao
}

@Database(entities = [LogIn::class], version = 1)
abstract class AppDatabase2 : RoomDatabase() {
    abstract fun logInDao() : LoginDao
}