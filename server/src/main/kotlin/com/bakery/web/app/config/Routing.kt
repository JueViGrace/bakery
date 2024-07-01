package com.bakery.web.app.config

import com.bakery.web.app.handler.response.DefaultHttpResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.util.IllegalFormatException

fun Application.configureRouting() {
    val logger = LoggerFactory.getLogger("Routing")
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logger.error("Unhandled error", cause)
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }

        exception<Exception> { call, cause ->
            logger.error("Unhandled error", cause)
            call.respond(
                status = HttpStatusCode.InternalServerError,
                DefaultHttpResponse.internalServerError<Nothing>()
            )
        }

        exception<RequestValidationException> { call, cause ->
            logger.error("Validation error", cause)
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = DefaultHttpResponse.badRequest<Nothing>(cause.reasons.joinToString())
            )
        }

        exception<BadRequestException> { call, cause ->
            logger.error("Request error", cause)
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = DefaultHttpResponse.badRequest<Nothing>("Invalid request")
            )
        }

        exception<IllegalStateException> { call, cause ->
            logger.error("State error", cause)
            call.respond(
                status = HttpStatusCode.Conflict,
                message = DefaultHttpResponse.conflict<Nothing>("Validation error: ${cause.message}")
            )
        }

        exception<IllegalFormatException> { call, cause ->
            logger.error("Format error", cause)
            call.respond(
                status = HttpStatusCode.BadRequest,
                message = DefaultHttpResponse.badRequest<Nothing>("${cause.message}")
            )
        }
//        status(HttpStatusCode.NotFound) { call, status ->
//            // logger.info("${call.callId}, $status")
//            call.respond(
//                status = status,
//                message = DefaultHttpResponse.notFound("The endpoint you wanted to search is not found")
//            )
//        }
        status(HttpStatusCode.UnsupportedMediaType) { call, status ->
            logger.error("Media error")
            call.respond(
                status = status,
                message = DefaultHttpResponse.badRequest<Nothing>("The provided request body is invalid")
            )
        }
//        status(HttpStatusCode.NoContent) { call, status ->
//            call.respond(
//                status = status,
//                message = "User with ${call.parameters["id"]} was deleted"
//            )
//        }
    }
    routing {
        staticResources("/static", "static")
    }
}
