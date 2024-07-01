package com.bakery.web.common

import io.ktor.server.util.*
import io.ktor.util.date.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

fun Instant.toDate(): Date {
    return this.toGMTDate().toJvmDate()
}

fun String.toDate(): Date {
    return try {
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(this)
    } catch (e: IllegalFormatException) {
        throw e
    }
}

fun String.toInstant(): Instant {
    return try {
        this.toDate().toInstant()
    } catch (e: IllegalArgumentException) {
        throw e
    }
}

fun String.validDate(): Boolean {
    return try {
        this.toDate()
        true
    }catch (e: IllegalFormatException) {
        false
        throw e
    }
}
