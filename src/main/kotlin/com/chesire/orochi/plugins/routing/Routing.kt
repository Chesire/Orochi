package com.chesire.orochi.plugins.routing

import com.chesire.orochi.routes.kitsu.kitsu
import com.chesire.orochi.routes.status.status
import io.ktor.application.Application
import io.ktor.client.HttpClient
import io.ktor.routing.routing

fun Application.configureRouting() {
    routing {
        status()
        kitsu()
    }
}
