package com.bakery.web.app.response

import com.bakery.web.common.Constants.UNEXPECTED_ERROR
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.Serializable
import java.util.*

object HttpResponse {
    // 200
    fun <T> ok(value: T): ResponseStatus.Success<T> {
        return ResponseStatus.Success(
            status = HttpStatusCode.OK.value,
            message = HttpStatusCode.OK.description,
            body = value
        )
    }

    // 201
    fun <T> created(value: T): ResponseStatus.Success<T> {
        return ResponseStatus.Success(
            status = HttpStatusCode.Created.value,
            message = HttpStatusCode.Created.description,
            body = value
        )
    }

    // 202
    fun <T> accepted(value: T): ResponseStatus.Success<T> {
        return ResponseStatus.Success(
            status = HttpStatusCode.Accepted.value,
            message = HttpStatusCode.Accepted.description,
            body = value
        )
    }

    // 204
    fun<T> noContent(value: T): ResponseStatus.Success<T> {
        return ResponseStatus.Success(
            status = HttpStatusCode.NoContent.value,
            message = HttpStatusCode.NoContent.description,
            body = value
        )
    }

    // 400
    fun badRequest(error: String): ResponseStatus.Failure {
        return ResponseStatus.Failure(
            status = HttpStatusCode.BadRequest.value,
            message = HttpStatusCode.BadRequest.description,
            error = error
        )
    }

    // 404
    fun notFound(error: String): ResponseStatus.Failure {
        return ResponseStatus.Failure(
            status = HttpStatusCode.NotFound.value,
            message = HttpStatusCode.NotFound.description,
            error = error
        )
    }

    // 500
    fun internalServerError(error: String = UNEXPECTED_ERROR): ResponseStatus.Failure {
        return ResponseStatus.Failure(
            status = HttpStatusCode.InternalServerError.value,
            message = HttpStatusCode.InternalServerError.description,
            error = error
        )
    }
}

@Serializable
sealed class ResponseStatus<out T> {
    @Serializable
    data class Success<T>(
        val status: Int,
        val message: String,
        val body: T,
    ) : ResponseStatus<T>()

    @Serializable
    data class Failure(
        val time: String = "${Date()}",
        val status: Int,
        val message: String,
        val error: String,
    ) : ResponseStatus<Nothing>()
}
