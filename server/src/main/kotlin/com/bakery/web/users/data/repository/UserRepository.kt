package com.bakery.web.users.data.repository

import com.bakery.web.users.data.model.UserDto

interface UserRepository {
    suspend fun findAll(): List<UserDto>
    suspend fun findOne(id: Int): UserDto?
    suspend fun saveUser(user: UserDto): UserDto?
    suspend fun updateUser(id: Int, user: UserDto): UserDto?
    suspend fun deleteUser(id: Int): Boolean
}
