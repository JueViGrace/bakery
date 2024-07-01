package com.bakery.web.app.config

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.bakery.web.app.handler.response.DefaultHttpResponse
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*

fun Application.configureAuth() {
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val myRealm = environment.config.property("jwt.realm").getString()
    install(Authentication) {
        jwt("auth-jwt") {
            realm = myRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )

            validate { credential ->
                if (credential.payload.getClaim("role").asString() == "admin") {
                    JWTPrincipal(credential.payload)
                } else {
                    respond(DefaultHttpResponse.unauthorized<Nothing>())
                    null
                }
            }

            challenge { defaultScheme, realm ->
                call.respond(DefaultHttpResponse.forbidden<Nothing>())
            }
        }

/*session<UserSession>("user_session") {
    // SESSION LOGIC

    validate { session ->

    }
}*/
    }
}
