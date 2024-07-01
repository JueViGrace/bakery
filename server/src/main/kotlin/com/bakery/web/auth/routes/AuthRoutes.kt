package com.bakery.web.auth.routes

import com.bakery.web.auth.model.LoginDto
import com.bakery.web.auth.model.RegisterDto
import com.bakery.web.auth.service.AuthService
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.authRoutes() {
    val authService: AuthService by inject<AuthService>()

    route("register") {
        post {
            val body = call.receive<RegisterDto>()

            val savedUser = authService.register(body)

            call.respond(savedUser)
        }
    }

    route("login") {
        post {
            val body = call.receive<LoginDto>()

            val newUser = authService.login(body)

            call.respond(newUser)
        }
    }
}
