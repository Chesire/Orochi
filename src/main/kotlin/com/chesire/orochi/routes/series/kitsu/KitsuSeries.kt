package com.chesire.orochi.routes.series.kitsu

import com.chesire.orochi.routes.ErrorDomain
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.coroutines.async
import org.koin.ktor.ext.inject

/**
 * Container route for all routes leading to series/kitsu/.
 */
fun Route.kitsuSeries() {
    val service by inject<KitsuSeriesService>()

    get("kitsu/{userId}/") {
        val userId = call.parameters["userId"]
        if (userId == null) {
            // TODO: Move into service
            call.respond(
                HttpStatusCode.BadRequest,
                ErrorDomain("Missing userId param from call", "Could not retrieve the user id")
            )
            return@get
        }

        listOf(
            async { service.requestUserAnime(userId) },
            //async { service.requestUserManga(userId) }
        )
            .map { it.await() }
            .map {
                it
                    .onSuccess {
                        call.respond(it.status, it.model)
                    }
                    .onFailure {
                        call.respond(it.status, it.model)
                    }
            }
    }
}
