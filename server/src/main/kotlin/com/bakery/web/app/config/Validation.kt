package com.bakery.web.app.config

import com.bakery.web.users.data.validation.validateUserDto
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureValidation() {
    install(RequestValidation) {
        validateUserDto()
        validate<Parameters> { parameters ->
            val errors = parameters["id"]?.map { it.isDigit() }
                ?: emptyList<Boolean>().also {
                    return@validate ValidationResult.Invalid("Parameter id must be provided")
                }

            if (errors.contains(false)) {
                ValidationResult.Invalid("Parameter id must be a valid number")
            } else {
                ValidationResult.Valid
            }
        }
    }
}
