package com.bakery.web.auth.validation

import com.bakery.web.auth.model.LoginDto
import com.bakery.web.common.Constants.emailRegex
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateLoginDto() {
    validate<LoginDto> { dto ->
        when {
            !emailRegex.matches(dto.email) -> ValidationResult.Invalid("Email must be a valid email")
            dto.password.isEmpty() -> ValidationResult.Invalid("Password must not be empty")
            else -> ValidationResult.Valid
        }
    }
}
