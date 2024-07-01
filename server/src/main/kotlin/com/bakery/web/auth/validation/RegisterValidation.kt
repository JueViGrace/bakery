package com.bakery.web.auth.validation

import com.bakery.web.auth.model.RegisterDto
import com.bakery.web.common.Constants.emailRegex
import com.bakery.web.common.validDate
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateRegisterDto() {
    validate<RegisterDto> { dto ->
        when {
            dto.name.isEmpty() -> ValidationResult.Invalid("Name must not be empty")
            dto.lastname.isEmpty() -> ValidationResult.Invalid("Last name must not be empty")
            !emailRegex.matches(dto.email) -> ValidationResult.Invalid("Email must be a valid email")
            dto.birthDate.validDate() -> ValidationResult.Invalid("Birth date is invalid")
            dto.phone.isEmpty() -> ValidationResult.Invalid("Phone must not be empty")
            else -> ValidationResult.Valid
        }
    }
}
