package com.chesire.orochi.routes.library

import com.chesire.orochi.routes.library.kitsu.kitsuLibrary
import io.ktor.routing.Routing
import io.ktor.routing.route

/**
 * Container route for all library routes.
 */
fun Routing.library() {
    route("library/") {
        kitsuLibrary()
    }
}
