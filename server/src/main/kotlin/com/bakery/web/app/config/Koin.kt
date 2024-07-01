package com.bakery.web.app.config

import com.bakery.web.app.di.coroutinesModule
import com.bakery.web.auth.di.authModule
import com.bakery.web.users.di.userModule
import io.ktor.server.application.*
import org.koin.core.logger.Level
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger(Level.DEBUG)
        modules(coroutinesModule + userModule + authModule)
    }
}
