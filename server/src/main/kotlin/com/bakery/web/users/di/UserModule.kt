package com.bakery.web.users.di

import com.bakery.web.users.data.repository.UserRepository
import com.bakery.web.users.service.UserService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userModule = module {
    singleOf(::UserRepository)

    singleOf(::UserService)
}
