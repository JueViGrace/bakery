package com.bakery.web.common

import io.ktor.server.util.*
import io.ktor.util.*
import java.util.*

object Constants {
    const val UNEXPECTED_ERROR = "An unexpected error occurred"

    const val SERVER_PORT = 5000
    const val SERVER_HOST = "0.0.0.0"
    const val JDBC_DATABASE_URL = "jdbc:postgresql://localhost:3308/bakery"
    const val JDBC_DRIVER = "org.postgresql.Driver"

    @OptIn(InternalAPI::class)
    val time = Date().toLocalDateTime().toString()
}
