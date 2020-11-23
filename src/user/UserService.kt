package com.kyoodong.user

import com.kyoodong.config.query
import com.kyoodong.entity.User
import com.kyoodong.user.model.UpdateUserRequest
import com.kyoodong.user.model.UserResponse
import io.ktor.features.*
import io.ktor.util.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob
import java.time.LocalDateTime

/**
 * 사용자를 관리하는 서비스
 */
@KtorExperimentalAPI
class UserService {

    /**
     * 아이디로 사용자를 검색하는 메소드
     * @param id 사용자 아이디
     */
    suspend fun getById(id: String) = query {
        User.findById(id)?.run(UserResponse.Companion::of)
            ?: throw NotFoundException()
    }

    /**
     * 사용자를 생성하는 메소드
     * @param id 사용자 아이디 (중복 X)
     * @param email 사용자 이메일 (중복 X)
     */
    suspend fun new(id: String, email: String) = query {
        User.new(id) {
            this.email = email
            this.gender = null
            this.age = null
            this.profileImage = null
            this.createdAt = LocalDateTime.now()
            this.updatedAt = LocalDateTime.now()
        }
    }

    /**
     * 사용자의 정보를 수정하는 메소드
     * @param id 수정할 사용자 아이디
     * @param request 수정될 새로운 정보. null 인 경우 수정 되지 않는다
     */
    suspend fun update(id: String, request: UpdateUserRequest) = query {
        val user = User.findById(id)
            ?: throw NotFoundException()

        user.apply {
            if (request.gender != null)
                gender = request.gender

            if (request.age != null)
                age = request.age

            updatedAt = LocalDateTime.now()
        }
    }

    /**
     * 사용자 프로필 이미지를 수정하는 메소드
     * @param id 수정할 사용자 아이디
     * @param byteArray 이미지 bytearray 데이터
     */
    suspend fun updateProfileImage(id: String, byteArray: ByteArray) = query {
        val user = User.findById(id)
            ?: throw NotFoundException()

        user.apply {
            profileImage = ExposedBlob(byteArray)
            updatedAt = LocalDateTime.now()
        }
    }
}