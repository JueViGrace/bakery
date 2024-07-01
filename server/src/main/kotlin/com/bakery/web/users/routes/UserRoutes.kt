package com.bakery.web.users.routes

import com.bakery.web.app.response.DefaultHttpResponse
import com.bakery.web.users.data.model.UserDto
import com.bakery.web.users.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.userRoutes() {
    val userService by inject<UserService>()
    val status = HttpStatusCode(HttpStatusCode.OK.value, HttpStatusCode.OK.description)

    route("users") {
        get {
            val users = userService.getAllUsers()

            call.respond(
                status = status.copy(
                    value = users.status,
                    description = users.message
                ),
                message = users
            )
        }

        get("/{id}") {
            val idParam = call.parameters["id"]
                ?: return@get call.respond(DefaultHttpResponse.badRequest("Parameter id must be provided"))

            if (idParam.map { it.isDigit() }.contains(false)) {
                return@get call.respond(DefaultHttpResponse.badRequest("Parameter id must be a valid number"))
            }

            val user = userService.getOneUser(idParam.toInt())

            call.respond(
                status = status.copy(
                    value = user.status,
                    description = user.message
                ),
                message = user
            )
        }

        post {
            val body = call.receive<UserDto>()

            val savedUser = userService.saveUser(body)

            call.respond(
                status = status.copy(
                    value = savedUser.status,
                    description = savedUser.message
                ),
                message = savedUser
            )
        }

        put("/{id}") {
            val idParam = call.parameters["id"]
                ?: return@put call.respond(DefaultHttpResponse.badRequest("Parameter id must be provided"))

            if (idParam.map { it.isDigit() }.contains(false)) {
                return@put call.respond(DefaultHttpResponse.badRequest("Parameter id must be a valid number"))
            }

            val body = call.receive<UserDto>()

            val savedUser = userService.updateUser(idParam.toInt(), body)

            call.respond(
                status = status.copy(
                    value = savedUser.status,
                    description = savedUser.message
                ),
                message = savedUser
            )
        }

        delete("/{id}") {
            val idParam = call.parameters["id"]
                ?: return@delete call.respond(DefaultHttpResponse.badRequest("Parameter id must be provided"))

            if (idParam.map { it.isDigit() }.contains(false)) {
                return@delete call.respond(DefaultHttpResponse.badRequest("Parameter id must be a valid number"))
            }

            val deletedUser = userService.deleteUser(idParam.toInt())

            call.respond(
                status = status.copy(
                    value = deletedUser.status,
                    description = deletedUser.message
                ),
                message = deletedUser
            )
        }
    }
}
