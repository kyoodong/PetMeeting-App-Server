package com.kyoodong.user.model

import com.kyoodong.entity.User
import java.time.LocalDateTime

data class UserResponse(
    val id: String,
    val email: String,
    val gender: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime) {

    companion object {
        fun of(user: User) =
            UserResponse(
                id = user.id.value,
                email = user.email,
                gender = user.gender,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
    }
}