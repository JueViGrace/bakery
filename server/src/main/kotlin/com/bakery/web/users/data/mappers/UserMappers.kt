package com.bakery.web.users.data.mappers

import com.bakery.web.users.data.model.UserDao
import com.bakery.web.users.data.model.UserDto

fun UserDao.toDto(): UserDto = UserDto(
    id = id.value,
    name = name,
    lastname = lastname
)
