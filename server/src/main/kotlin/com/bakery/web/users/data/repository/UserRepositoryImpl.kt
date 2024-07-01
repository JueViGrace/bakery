package com.bakery.web.users.data.repository

import com.bakery.web.common.toInstant
import com.bakery.web.users.data.mappers.toDto
import com.bakery.web.users.data.model.UserDao
import com.bakery.web.users.data.model.UserDto
import com.bakery.web.users.data.model.UserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync
import kotlin.coroutines.CoroutineContext

class UserRepositoryImpl(
    private val coroutineContext: CoroutineContext
) : UserRepository {
    override suspend fun findAll(): List<UserDto> {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserDao.all().map { userDao ->
                userDao.toDto()
            }
        }.await()
    }

    override suspend fun findOne(id: Int): UserDto? {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserDao.findById(id)?.toDto()
        }.await()
    }

    override suspend fun findOneByEmail(email: String): UserDto? {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            val users = UserDao.find {
                UserTable.email eq email
            }.limit(1).map { dao ->
                dao.toDto()
            }

            return@suspendedTransactionAsync when {
                users.size > 1 -> {
                    error("Email list threw more than one element")
                }
                users.isEmpty() -> {
                    null
                }
                else -> {
                    users.first()
                }
            }
        }.await()
    }

    override suspend fun saveUser(user: UserDto): UserDto {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserDao.new {
                name = user.name
                lastname = user.lastname
                email = user.email
                birthDate = user.birthDate.toInstant()
                phone = user.phone
            }.toDto()
        }.await()
    }

    override suspend fun updateUser(id: Int, user: UserDto): UserDto? {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserDao.findByIdAndUpdate(
                id = id,
                block = { dao ->
                    dao.name = user.name
                    dao.lastname = user.lastname
                }
            )?.toDto()
        }.await()
    }

    override suspend fun deleteUser(id: Int): Boolean {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserTable.deleteWhere {
                UserTable.id eq id
            } == 1
        }.await()
    }
}
