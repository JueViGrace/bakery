package com.bakery.web.users.data.validation

import com.bakery.web.users.data.model.UserDto
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateUserDto() {
    validate<UserDto> { dto ->
        when {
            dto.name.isEmpty() -> ValidationResult.Invalid("Name must not be empty")
            dto.lastname.isEmpty() -> ValidationResult.Invalid("Last name must not be empty")
            else -> ValidationResult.Valid
        }
    }
}
