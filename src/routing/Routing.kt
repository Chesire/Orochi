package com.chesire.routing

import com.chesire.routing.status.status
import io.ktor.application.Application
import io.ktor.routing.routing

@Suppress("UnusedPrivateMember")
fun Application.configureRouting() {
    routing {
        status()
    }
}
