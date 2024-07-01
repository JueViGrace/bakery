package com.bakery.web.users.data.mappers

import com.bakery.web.auth.model.RegisterDto
import com.bakery.web.common.toDate
import com.bakery.web.users.data.model.UserDao
import com.bakery.web.users.data.model.UserDto

fun UserDao.toDto(): UserDto = UserDto(
    id = id.value,
    name = name,
    lastname = lastname,
    email = email,
    birthDate = birthDate.toDate().toString(),
    phone = phone,
    createdAt = createdAt.toDate().toString()
)

fun RegisterDto.toUserDto(): UserDto = UserDto(
    name = name,
    lastname = lastname,
    email = email,
    birthDate = birthDate,
    phone = phone,
)
