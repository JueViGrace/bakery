package com.bakery.web.database.driver

import com.bakery.web.common.Constants.JDBC_DATABASE_URL
import com.bakery.web.common.Constants.JDBC_DRIVER
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DriverFactory {
    private val dataSource: HikariDataSource = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = JDBC_DATABASE_URL
            driverClassName = JDBC_DRIVER
            username = "jvg_25"
            password = "root"
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
    )

    fun initDatabase() {
        Database.connect(dataSource)
    }
}
