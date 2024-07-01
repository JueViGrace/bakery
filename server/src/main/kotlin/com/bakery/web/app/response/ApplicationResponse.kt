package com.bakery.web.app.response

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
    fun badRequest(error: String): ApplicationResponse<Nothing> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.BadRequest.value,
            message = HttpStatusCode.BadRequest.description,
            error = error
        )
    }

    // 404
    fun notFound(error: String): ApplicationResponse<Nothing> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.NotFound.value,
            message = HttpStatusCode.NotFound.description,
            error = error
        )
    }

    // 500
    fun internalServerError(error: String = UNEXPECTED_ERROR): ApplicationResponse<Nothing> {
        return ApplicationResponse(
            time = time,
            status = HttpStatusCode.InternalServerError.value,
            message = HttpStatusCode.InternalServerError.description,
            error = error
        )
    }
}
