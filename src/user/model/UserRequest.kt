package com.kyoodong.user.model

import io.ktor.http.content.*
import java.time.LocalDateTime

data class UserRequest(
    val id: String,
    val email: String,
    val gender: String?,
    val age: Int?,
    val profileImage: MultiPartData?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?)


data class UpdateUserRequest(
    val id: String,
    val gender: String?,
    val age: Int?
)