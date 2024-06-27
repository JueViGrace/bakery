package com.bakery.web.database.di

import com.bakery.web.database.driver.DriverFactory
import com.bakery.web.database.helper.DbHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::DriverFactory)

    singleOf(::DbHelper)
}
