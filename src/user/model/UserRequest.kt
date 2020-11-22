package com.kyoodong.user.model

import java.time.LocalDateTime

data class UserRequest(
    val id: String,
    val email: String,
    val gender: String?,
    val createdAt: LocalDateTime?,
    val updatedAt: LocalDateTime?)


data class UpdateGenderRequest(
    val id: String,
    val gender: String
)