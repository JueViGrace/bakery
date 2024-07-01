package com.bakery.web.app

import com.bakery.web.app.config.configureHTTP
import com.bakery.web.app.config.configureKoin
import com.bakery.web.app.config.configureMonitoring
import com.bakery.web.app.config.configureRouting
import com.bakery.web.app.config.configureSerialization
import com.bakery.web.app.config.configureValidation
import com.bakery.web.app.routes.appRoutes
import com.bakery.web.common.Constants.SERVER_HOST
import com.bakery.web.common.Constants.SERVER_PORT
import com.bakery.web.database.driver.DriverFactory.initDatabase
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = SERVER_HOST,
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    // Basic configuration
    configureKoin()
    configureRouting()
    configureValidation()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    initDatabase()

    // Application routes
    appRoutes()
}
