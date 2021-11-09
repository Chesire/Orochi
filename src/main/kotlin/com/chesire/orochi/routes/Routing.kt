package com.chesire.orochi.routes

import com.chesire.orochi.routes.kitsu.kitsu
import com.chesire.orochi.routes.status.status
import io.ktor.application.Application
import io.ktor.routing.routing

fun Application.startRouting() {
    routing {
        status()
        kitsu()
    }
}
