package com.chesire.orochi.plugins.routing

import com.chesire.orochi.plugins.routing.kitsu.kitsu
import com.chesire.orochi.plugins.routing.status.status
import io.ktor.application.Application
import io.ktor.routing.routing

fun Application.configureRouting() {
    routing {
        status()
        kitsu()
    }
}
