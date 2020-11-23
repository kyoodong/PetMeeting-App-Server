package com.kyoodong.user.model

import com.kyoodong.entity.User
import java.time.LocalDateTime

data class UserResponse(
    val id: String,
    val email: String,
    val gender: String?,
    val age: Int?,
    val profileImage: ByteArray?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime) {

    companion object {
        fun of(user: User) =
            UserResponse(
                id = user.id.value,
                email = user.email,
                gender = user.gender,
                age = user.age,
                profileImage = user.profileImage?.bytes,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
    }
}