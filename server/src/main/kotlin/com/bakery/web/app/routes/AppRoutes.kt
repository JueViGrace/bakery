package com.bakery.web.app.routes

import com.bakery.web.users.routes.userRoutes
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.appRoutes() {
    routing {
        route("/api") {
            userRoutes()
        }
    }
}
