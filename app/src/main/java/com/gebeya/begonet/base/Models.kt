package com.gebeya.begonet.base

data class User(
    val name: String = "",
    val phoneNumber: String = "",
    val userId : String = "",
    val email: String = "",
    val role: String = ""
)

enum class Role {
    NGO, VOLUNTEER
}