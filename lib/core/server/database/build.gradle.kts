plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.sqldelight)
}

group = "com.bakery.core.server.database"

dependencies {
    implementation(projects.lib.core.shared.database)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)

    // Sqldelight
    implementation(libs.sqldelight.coroutines.extensions)
    implementation(libs.sqldelight.async.extensions)

    // Sqlite
    implementation(libs.sqldelight.sqlite.driver)
    implementation(libs.sqlite)

    // Postgres
    // implementation(libs.sqldelight.postgres.driver)
    // implementation(libs.postgresql)
}

sqldelight {
    databases {
        create("BakerySvDb") {
            packageName.set("com.bakery.core.server.database")
            dialect(libs.sqldelight.sqlite.dialect)
            generateAsync.set(true)
        }
        // If server needs Pg database uncomment
//        create("BakeryPgDb") {
//            packageName.set("com.bakery.core.database")
//            dialect(libs.sqldelight.postgres.dialect)
//            generateAsync.set(true)
//        }
    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
