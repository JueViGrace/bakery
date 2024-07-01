package com.bakery.web.users.service

import com.bakery.web.app.response.ApplicationResponse
import com.bakery.web.common.Constants.UNEXPECTED_ERROR
import com.bakery.web.common.Constants.time
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
                    ApplicationResponse(
                        status = HttpStatusCode.OK.value,
                        message = HttpStatusCode.OK.description,
                        body = users
                    )
                } else {
                    ApplicationResponse(
                        time = time,
                        status = HttpStatusCode.NotFound.value,
                        message = HttpStatusCode.NotFound.description,
                        error = "Users not found"
                    )
                }
            }
        } catch (e: Exception) {
            println(e)
            return ApplicationResponse(
                time = time,
                status = HttpStatusCode.InternalServerError.value,
                message = HttpStatusCode.InternalServerError.description,
                error = UNEXPECTED_ERROR
            )
        }
    }

    suspend fun getOneUser(id: Int): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val user = userRepository.findOne(id)

                return@withContext if (user != null) {
                    ApplicationResponse(
                        status = HttpStatusCode.OK.value,
                        message = HttpStatusCode.OK.description,
                        body = user
                    )
                } else {
                    ApplicationResponse(
                        time = time,
                        status = HttpStatusCode.NotFound.value,
                        message = HttpStatusCode.NotFound.description,
                        error = "User with id $id doesn't exists"
                    )
                }
            }
        } catch (e: Exception) {
            return ApplicationResponse(
                time = time,
                status = HttpStatusCode.InternalServerError.value,
                message = HttpStatusCode.InternalServerError.description,
                error = UNEXPECTED_ERROR
            )
        }
    }

    suspend fun saveUser(userDto: UserDto): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val savedUser = userRepository.saveUser(userDto)

                return@withContext if (savedUser != null) {
                    ApplicationResponse(
                        status = HttpStatusCode.Created.value,
                        message = HttpStatusCode.Created.description,
                        body = savedUser
                    )
                } else {
                    ApplicationResponse(
                        time = time,
                        status = HttpStatusCode.NotFound.value,
                        message = HttpStatusCode.NotFound.description,
                        error = "Failed to create user"
                    )
                }
            }
        } catch (e: Exception) {
            return ApplicationResponse(
                time = time,
                status = HttpStatusCode.InternalServerError.value,
                message = HttpStatusCode.InternalServerError.description,
                error = UNEXPECTED_ERROR
            )
        }
    }

    suspend fun updateUser(id: Int, userDto: UserDto): ApplicationResponse<UserDto?> {
        return try {
            withContext(coroutineContext) {
                val savedUser = userRepository.updateUser(id = id, user = userDto)

                return@withContext if (savedUser != null) {
                    ApplicationResponse(
                        status = HttpStatusCode.Created.value,
                        message = HttpStatusCode.Created.description,
                        body = savedUser
                    )
                } else {
                    ApplicationResponse(
                        time = time,
                        status = HttpStatusCode.NotFound.value,
                        message = HttpStatusCode.NotFound.description,
                        error = "Failed to create user"
                    )
                }
            }
        } catch (e: Exception) {
            return ApplicationResponse(
                time = time,
                status = HttpStatusCode.InternalServerError.value,
                message = HttpStatusCode.InternalServerError.description,
                error = UNEXPECTED_ERROR
            )
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
                    ApplicationResponse(
                        status = HttpStatusCode.NoContent.value,
                        message = HttpStatusCode.NoContent.description,
                        body = "User with id $id was deleted successfully"
                    )
                } else {
                    ApplicationResponse(
                        time = time,
                        status = HttpStatusCode.BadRequest.value,
                        message = HttpStatusCode.BadRequest.description,
                        error = "Unable to delete user"
                    )
                }
            }
        } catch (e: Exception) {
            return ApplicationResponse(
                time = time,
                status = HttpStatusCode.InternalServerError.value,
                message = HttpStatusCode.InternalServerError.description,
                error = UNEXPECTED_ERROR
            )
        }
    }
}
