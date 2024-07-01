package com.bakery.web.app.handler.routes

import com.bakery.web.auth.routes.authRoutes
import com.bakery.web.users.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.appRoutes() {
    routing {
        route("/api") {
            userRoutes()
            authRoutes()
        }
    }
}
