package com.chesire.orochi.routes.series.kitsu

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.get
import org.koin.ktor.ext.inject

/**
 * Container route for all routes leading to series/kitsu/.
 */
fun Route.kitsuSeries() {
    val service by inject<KitsuSeriesService>()

    get("kitsu/{userId}/") {
        val userId = call.parameters["userId"]
        val response = service.requestUserSeries(userId!!)
        call.respondText(response)
    }
}
