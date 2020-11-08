package com.gebeya.begonet.base

data class User(
    val name: String = "",
    val phoneNumber: String = "",
    val userId : String = "",
    val email: String = "",
    val role: String = "",
    val profilePicUri: String = ""
)

enum class Role {
    NGO, VOLUNTEER
}

data class ProjectStatus(
    val status: String = "",
    val imageUrl: Int = -1
)