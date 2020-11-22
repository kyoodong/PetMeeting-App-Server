package com.kyoodong.user

import com.kyoodong.user.model.UpdateGenderRequest
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
        get("/{id}") {
            val id = call.parameters["id"]
                ?: throw BadRequestException("파라미터 id 가 null 입니다.")
            call.respond(service.getById(id))
        }

        post {
            withContext(Dispatchers.IO) {
                val body = call.receive<UserRequest>()
                service.new(body.id, body.email)
                call.response.status(HttpStatusCode.Created)
            }
        }

        put("/{id}") {
            withContext(Dispatchers.IO) {
                val id = call.parameters["id"]
                    ?: throw BadRequestException("파라미터 id가 null 입니다.")

                val body = call.receive<UpdateGenderRequest>()
                service.renew(id, body)
                call.response.status(HttpStatusCode.NoContent)
            }
        }
    }
}