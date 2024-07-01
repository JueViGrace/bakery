package com.bakery.web.app

import com.bakery.web.app.config.configureAuth
import com.bakery.web.app.config.configureHTTP
import com.bakery.web.app.config.configureKoin
import com.bakery.web.app.config.configureMonitoring
import com.bakery.web.app.config.configureRouting
import com.bakery.web.app.config.configureSerialization
import com.bakery.web.app.config.configureValidation
import com.bakery.web.app.handler.routes.appRoutes
import com.bakery.web.database.driver.DriverFactory.initDatabase
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) {
    EngineMain.main(args)
    /*embeddedServer(
        Netty,
        port = SERVER_PORT,
        host = SERVER_HOST,
        module = Application::module
    ).start(wait = true)*/
}

fun Application.module() {
    // Basic configuration
    configureAuth()
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
