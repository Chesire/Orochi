package com.chesire.orochi.routes.auth

import com.chesire.orochi.routes.auth.kitsu.kitsuAuth
import io.ktor.routing.Routing
import io.ktor.routing.route

/**
 * Container route for all auth routes.
 */
fun Routing.auth() {
    route("auth/") {
        kitsuAuth()
    }
}
