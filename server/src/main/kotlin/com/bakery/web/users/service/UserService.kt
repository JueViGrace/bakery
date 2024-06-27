package com.bakery.web.users.service

import com.bakery.web.app.response.HttpResponse
import com.bakery.web.app.response.ResponseStatus
import com.bakery.web.users.data.mappers.toDto
import com.bakery.web.users.data.model.UserDto
import com.bakery.web.users.data.repository.UserRepository
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UserService(
    private val userRepository: UserRepository,
    private val coroutineContext: CoroutineContext
) {
    suspend fun getAllUsers(): ResponseStatus<List<UserDto>> {
        return withContext(coroutineContext) {
            val users = userRepository.findAll().map { user -> user.toDto() }

            return@withContext if (users.isNotEmpty()) {
                HttpResponse.ok(users)
            } else {
                HttpResponse.notFound("Users not found")
            }
        }
    }
}
