package com.bakery.web.users.service

import com.bakery.web.app.handler.response.ApplicationResponse
import com.bakery.web.app.handler.response.DefaultHttpResponse
import com.bakery.web.users.data.model.UserDto
import com.bakery.web.users.data.repository.UserRepository
import io.ktor.http.*
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class UserService(
    private val userRepository: UserRepository,
    private val coroutineContext: CoroutineContext
) {
    suspend fun getAllUsers(): ApplicationResponse<List<UserDto>> {
        return try {
            withContext(coroutineContext) {
                val users = userRepository.findAll()

                return@withContext if (users.isNotEmpty()) {
                    DefaultHttpResponse.ok(users)
                } else {
                    DefaultHttpResponse.notFound("Users not found")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }

    suspend fun getOneUser(id: Int): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val user = userRepository.findOne(id)

                return@withContext if (user != null) {
                    DefaultHttpResponse.ok(user)
                } else {
                    DefaultHttpResponse.notFound("User with id $id doesn't exists")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }

    suspend fun getOneUserByEmail(email: String): ApplicationResponse<UserDto> {
        return try {
            withContext(coroutineContext) {
                val user = userRepository.findOneByEmail(email)

                return@withContext if (user != null) {
                    DefaultHttpResponse.ok(user)
                } else {
                    DefaultHttpResponse.notFound("User with email $email doesn't exists")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }

    suspend fun createUser(userDto: UserDto): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val existingUser = userRepository.findOneByEmail(userDto.email)

                if (existingUser != null) {
                    return@withContext DefaultHttpResponse.conflict("${userDto.email} is already in use")
                }

                val savedUser = userRepository.saveUser(userDto)

                return@withContext if (savedUser != null) {
                    DefaultHttpResponse.created(savedUser)
                } else {
                    DefaultHttpResponse.badRequest("Failed to create user")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }

    suspend fun updateUser(id: Int, userDto: UserDto): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val savedUser = userRepository.updateUser(id = id, user = userDto)

                return@withContext if (savedUser != null) {
                    DefaultHttpResponse.ok(savedUser)
                } else {
                    DefaultHttpResponse.badRequest("Failed to update user")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }

    suspend fun deleteUser(id: Int): ApplicationResponse<String> {
        return try {
            withContext(coroutineContext) {
                val user = userRepository.findOne(id)
                    ?: return@withContext ApplicationResponse(
                        status = HttpStatusCode.NotFound.value,
                        message = HttpStatusCode.NotFound.description,
                        body = "User with id $id was not found"
                    )

                val deleted = userRepository.deleteUser(user.id!!)

                if (deleted) {
                    DefaultHttpResponse.noContent("User with id $id was deleted successfully")
                } else {
                    DefaultHttpResponse.badRequest("Unable to delete user")
                }
            }
        } catch (e: Exception) {
            DefaultHttpResponse.internalServerError()
        }
    }
}
