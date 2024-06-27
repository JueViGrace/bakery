package com.bakery.web.app.config

import com.bakery.web.app.response.HttpResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logger.error("Unhandled error", cause)
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        status(HttpStatusCode.NotFound) { call, status ->
            // logger.info("${call.callId}, $status")
            call.respond(
                HttpResponse.notFound(
                    "The endpoint you wanted to search is not found"
                )
            )
        }
    }
    routing {
        staticResources("/static", "static")
    }
}
