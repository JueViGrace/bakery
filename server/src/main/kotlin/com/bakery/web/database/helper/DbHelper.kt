package com.bakery.web.database.helper

import com.bakery.web.BakeryDB
import com.bakery.web.database.driver.DriverFactory
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class DbHelper(
    private val driverFactory: DriverFactory
) {
    private var db: BakeryDB? = null

    private val mutex = Mutex()

    suspend fun <Result : Any?> withDatabase(block: suspend (BakeryDB) -> Result): Result = mutex.withLock {
        if (db == null) {
            db = createDb(driverFactory)
        }

        return@withLock block(db!!)
    }

    private fun createDb(driverFactory: DriverFactory): BakeryDB {
        return BakeryDB(driverFactory.createDriver())
    }
}
