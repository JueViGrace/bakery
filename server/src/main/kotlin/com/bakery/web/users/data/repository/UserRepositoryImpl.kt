package com.bakery.web.users.data.repository

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

    override suspend fun saveUser(user: UserDto): UserDto {
        return suspendedTransactionAsync(
            coroutineContext
        ) {
            UserDao.new {
                name = user.name
                lastname = user.lastname
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
