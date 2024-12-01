rootProject.name = "bakery-starter"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

include(":client:composeApp")
include(":server")
include(":lib")

// core client library
include(":lib:core:client:api")
include(":lib:core:client:database")
include(":lib:core:client:di")
include(":lib:core:client:presentation")
include(":lib:core:client:resources")
include(":lib:core:client:types")

// core server library
include(":lib:core:server:api")
include(":lib:core:server:database")
include(":lib:core:server:types")
include(":lib:core:server:util")

// core shared library
include(":lib:core:shared:di")
include(":lib:core:shared:types")

// auth library
include(":lib:auth:shared:types")

//  auth client
include(":lib:auth:client:data")
include(":lib:auth:client:domain")
include(":lib:auth:client:presentation")

// auth server
include(":lib:auth:server")

// user library
include(":lib:user:shared:types")

// user server
include(":lib:user:server")

// user client


// product library
include(":lib:product:shared:types")

// product server
include(":lib:product:server")

// product client

// order library
include(":lib:order:shared:types")

// order server
include(":lib:order:server")
