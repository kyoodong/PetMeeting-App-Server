package com.kyoodong.user

import com.kyoodong.user.model.UpdateUserRequest
import com.kyoodong.user.model.UserRequest
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@KtorExperimentalAPI
fun Routing.user(service: UserService) {
    route("users") {

        /**
         * 아이디로 사용자를 검색하는 API
         */
        get("/{id}") {
            val id = call.parameters["id"]
                ?: throw BadRequestException("파라미터 id 가 null 입니다.")
            call.respond(service.getById(id))
        }

        /**
         * 사용자를 생성하는 API
         */
        post {
            withContext(Dispatchers.IO) {
                val body = call.receive<UserRequest>()
                service.new(body.id, body.email)
                call.response.status(HttpStatusCode.Created)
            }
        }

        /**
         * 사용자의 정보를 수정하는 메소드
         */
        put("/{id}") {
            withContext(Dispatchers.IO) {
                val id = call.parameters["id"]
                    ?: throw BadRequestException("파라미터 id가 null 입니다.")

                val body = call.receive<UpdateUserRequest>()
                service.update(id, body)
                call.response.status(HttpStatusCode.NoContent)
            }
        }
    }
}