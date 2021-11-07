package com.chesire.orochi.plugins.routing

import com.chesire.orochi.plugins.routing.status.status
import io.ktor.application.Application
import io.ktor.routing.routing

@Suppress("UnusedPrivateMember")
fun Application.configureRouting() {
    routing {
        status()
    }
}
