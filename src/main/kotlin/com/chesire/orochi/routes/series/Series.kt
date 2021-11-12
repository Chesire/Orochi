package com.chesire.orochi.routes.series

import com.chesire.orochi.routes.series.kitsu.kitsuSeries
import io.ktor.routing.Routing
import io.ktor.routing.route

/**
 * Container route for all series routes.
 */
fun Routing.series() {
    route("series/") {
        kitsuSeries()
    }
}
