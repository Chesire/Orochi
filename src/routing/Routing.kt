package com.chesire.routing

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing

@Suppress("UnusedPrivateMember")
fun Application.configureRouting(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
