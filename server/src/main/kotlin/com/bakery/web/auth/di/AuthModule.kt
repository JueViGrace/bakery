package com.bakery.web.auth.di

import com.bakery.web.auth.service.AuthService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authModule = module {
    singleOf(::AuthService)
}
