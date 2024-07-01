package com.bakery.web.app.handler.response

import com.bakery.web.common.Constants.UNEXPECTED_ERROR
import com.bakery.web.common.Constants.time
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationResponse<T>(
    val time: String = "",
    val status: Int,
    val message: String,
    val body: T? = null,
    val error: String? = null,
)

object DefaultHttpResponse {
    // 200
    fun <T> ok(value: T): ApplicationResponse<T> {
        return ApplicationResponse(
            status = HttpStatusCode.OK.value,
            message = HttpStatusCode.OK.description,
            body = value
        )
    }

    // 201
    fun <T> created(value: T): ApplicationResponse<T> {
        return ApplicationResponse(
            status = HttpStatusCode.Created.value,
            message = HttpStatusCode.Created.description,
            body = value
        )
    }

    // 202
    fun <T> accepted(value: T): ApplicationResponse<T> {
        return ApplicationResponse(
            status = HttpStatusCode.Accepted.value,
            message = HttpStatusCode.Accepted.description,
            body = value
        )
    }

    // 204
    fun<T> noContent(value: T): ApplicationResponse<T> {
        return ApplicationResponse(
            status = HttpStatusCode.NoContent.value,
            message = HttpStatusCode.NoContent.description,
            body = value
        )
    }

    // 400
    fun<T> badRequest(error: String): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.BadRequest.value,
            message = HttpStatusCode.BadRequest.description,
            body = null,
            error = error
        )
    }

    // 401
    fun<T> unauthorized(): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.Unauthorized.value,
            message = HttpStatusCode.Unauthorized.description,
            body = null,
            error = "You are not authorized to access this endpoint"
        )
    }

    // 403
    fun<T> forbidden(): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.Forbidden.value,
            message = HttpStatusCode.Forbidden.description,
            body = null,
            error = "You are forbidden to access this endpoint"
        )
    }

    // 404
    fun<T> notFound(error: String): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.NotFound.value,
            message = HttpStatusCode.NotFound.description,
            body = null,
            error = error
        )
    }

    // 409
    fun<T> conflict(error: String): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.Conflict.value,
            message = HttpStatusCode.Conflict.description,
            body = null,
            error = error
        )
    }

    // 500
    fun<T> internalServerError(error: String = UNEXPECTED_ERROR): ApplicationResponse<T> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.InternalServerError.value,
            message = HttpStatusCode.InternalServerError.description,
            body = null,
            error = error
        )
    }
}
