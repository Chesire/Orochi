package com.chesire.orochi.plugins.statuspage

import com.chesire.orochi.routes.ErrorDomain
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(
                HttpStatusCode.InternalServerError,
                ErrorDomain("internal error", "Unknown error has occurred - ${cause.localizedMessage}")
            )
        }
    }
}
