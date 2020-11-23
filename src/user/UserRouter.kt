package com.kyoodong.user

import com.kyoodong.user.model.UpdateUserRequest
import com.kyoodong.user.model.UserRequest
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.OutputStream


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
                call.response.status(HttpStatusCode.OK)
            }
        }

        /**
         * 사용자의 정보를 수정하는 API
         */
        put("/{id}") {
            withContext(Dispatchers.IO) {
                val id = call.parameters["id"]
                    ?: throw BadRequestException("파라미터 id가 null 입니다.")

                val body = call.receive<UpdateUserRequest>()
                service.update(id, body)
                call.response.status(HttpStatusCode.OK)
            }
        }

        /**
         * 사용자의 프로필 사진을 변경하는 API
         */
        post("/{id}/profile-image") {
            withContext(Dispatchers.IO) {
                val id = call.parameters["id"]
                    ?: throw BadRequestException("파라미터 id가 null 입니다.")

                val body = call.receiveMultipart()
                body.forEachPart { part ->
                    when (part) {
                        is PartData.FileItem -> {
                            val baos = ByteArrayOutputStream()
                            part.streamProvider().use { input ->
                                baos.buffered().use {output ->
                                    input.copyTo(output)
                                }
                            }

                            service.updateProfileImage(id, baos.toByteArray())
                        }
                    }
                    part.dispose()
                }

                call.response.status(HttpStatusCode.OK)
            }
        }
    }
}
