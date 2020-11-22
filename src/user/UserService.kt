package com.kyoodong.user

import com.kyoodong.config.query
import com.kyoodong.entity.User
import com.kyoodong.user.model.UpdateGenderRequest
import com.kyoodong.user.model.UserRequest
import com.kyoodong.user.model.UserResponse
import io.ktor.features.*
import io.ktor.util.*
import java.time.LocalDateTime

@KtorExperimentalAPI
class UserService {

    suspend fun getById(id: String) = query {
        User.findById(id)?.run(UserResponse.Companion::of)
            ?: throw NotFoundException()
    }

    suspend fun new(id: String, email: String) = query {
        User.new(id) {
            this.email = email
            this.gender = null
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }
    }

    suspend fun renew(id: String, request: UpdateGenderRequest) = query {
        val user = User.findById(id)
            ?: throw NotFoundException()
        user.apply {
            gender = request.gender
            updatedAt = LocalDateTime.now()
        }
    }
}