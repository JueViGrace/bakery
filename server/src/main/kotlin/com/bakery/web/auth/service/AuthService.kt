package com.bakery.web.auth.service

import com.bakery.web.app.handler.response.ApplicationResponse
import com.bakery.web.auth.model.LoginDto
import com.bakery.web.auth.model.RegisterDto
import com.bakery.web.users.data.mappers.toUserDto
import com.bakery.web.users.data.model.UserDto
import com.bakery.web.users.service.UserService
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class AuthService(
    private val coroutineContext: CoroutineContext,
    private val userService: UserService
) {
    suspend fun register(registerDto: RegisterDto): ApplicationResponse<UserDto?> {
        return withContext(coroutineContext) {
            return@withContext userService.createUser(registerDto.toUserDto())
        }
    }

    suspend fun login(loginDto: LoginDto): ApplicationResponse<UserDto> {
        return withContext(coroutineContext) {
            return@withContext userService.getOneUserByEmail(loginDto.email)
        }
    }
}
