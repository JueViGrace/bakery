package com.bakery.web.users.data.repository

import com.bakery.web.database.helper.DbHelper
import combakeryweb.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class UserRepository(
    private val dbHelper: DbHelper,
    private val scope: CoroutineScope
) {
    suspend fun findAll(): List<User> = scope.async {
        dbHelper.withDatabase { db ->
            db.userQueries
                .findAll()
                .executeAsList()
        }
    }.await()
}
