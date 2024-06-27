package com.bakery.web.users.data.mappers

import com.bakery.web.users.data.model.UserDto
import combakeryweb.User

fun User.toDto(): UserDto = UserDto(
    id = id.toLong(),
    name = name,
    lastname = lastname
)

fun UserDto.toEntity(): User = User(
    id = id.toInt(),
    name = name,
    lastname = lastname
)
