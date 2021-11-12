package com.chesire.orochi.routes

import com.chesire.orochi.routes.auth.auth
import com.chesire.orochi.routes.series.series
import com.chesire.orochi.routes.status.status
import io.ktor.application.Application
import io.ktor.routing.routing

/**
 * Builds up the routes and sets them in the [Application].
 */
fun Application.startRouting() {
    routing {
        status()
        auth()
        series()
    }
}
