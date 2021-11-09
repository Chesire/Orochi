package com.chesire.orochi.routes.kitsu

import com.chesire.orochi.routes.kitsu.auth.kitsuAuth
import io.ktor.routing.Routing
import io.ktor.routing.route

/**
 * Container route for all routes leading to kitsu/.
 */
fun Routing.kitsu() {
    route("kitsu/") {
        kitsuAuth()
    }
}
