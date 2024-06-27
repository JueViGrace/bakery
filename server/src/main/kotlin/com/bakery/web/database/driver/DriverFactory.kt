package com.bakery.web.database.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import com.bakery.web.common.Constants.JDBC_DATABASE_URL
import com.bakery.web.common.Constants.JDBC_DRIVER
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

class DriverFactory {
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

    fun createDriver(): SqlDriver {
        return dataSource.asJdbcDriver()
    }
}
