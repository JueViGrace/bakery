package com.bakery.web.users.data.validation

import com.bakery.web.common.Constants.emailRegex
import com.bakery.web.common.validDate
import com.bakery.web.users.data.model.UserDto
import io.ktor.server.plugins.requestvalidation.*

fun RequestValidationConfig.validateUserDto() {
    validate<UserDto> { dto ->
        when {
            dto.id == null -> ValidationResult.Invalid("Id must not be null")
            dto.id <= 0 -> ValidationResult.Invalid("Id must be a valid id")
            dto.name.isEmpty() -> ValidationResult.Invalid("Name must not be empty")
            dto.lastname.isEmpty() -> ValidationResult.Invalid("Last name must not be empty")
            !emailRegex.matches(dto.email) -> ValidationResult.Invalid("Email must be a valid email")
            dto.birthDate.validDate() -> ValidationResult.Invalid("Birth date is invalid")
            dto.phone.isEmpty() -> ValidationResult.Invalid("Phone must not be empty")
            else -> ValidationResult.Valid
        }
    }
}
