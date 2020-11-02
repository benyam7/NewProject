package com.gebeya.begonet.data.model

import android.annotation.SuppressLint
import androidx.annotation.RequiresPermission
import androidx.room.*

@Entity(tableName = "Register")
data class SignUp(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val name: String,
    @ColumnInfo val phone: Long,
    @ColumnInfo val email: String,
    @ColumnInfo val password: String,
    @ColumnInfo val confirmPassword: String,
    @ColumnInfo(name = "created_at") val createdAt: String
)



data class LogIn(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val email: String,
    @ColumnInfo val phone: Long,
    @ColumnInfo val password: String,
)

@Dao
interface RegisterDao{
    @Insert
    fun addInformation(signup: SignUp)

    @Query("SELECT * FROM Register")
    fun getAll(): List<SignUp>


}

@Dao
interface LoginDao{
    @SuppressLint("SupportAnnotationUsage")
    @RequiresPermission.Read
    fun readInforamation(login : LogIn)
}