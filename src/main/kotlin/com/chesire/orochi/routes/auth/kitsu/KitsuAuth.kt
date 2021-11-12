package com.chesire.orochi.routes.auth.kitsu

import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

/**
 * Container route for all routes leading to auth/kitsu/.
 */
fun Route.kitsuAuth() {
    val service by inject<KitsuAuthService>()

    post("kitsu/") {
        val input = call.receive<KitsuInputAuthDomain>()
        val result = service.requestAuthToken(input.username, input.password)

        result
            .onSuccess {
                call.respond(
                    it.status,
                    service.createKitsuOutputAuthDomain(it.model)
                )
            }
            .onFailure {
                call.respond(
                    it.status,
                    service.createErrorDomain(it.model)
                )
            }
    }
}
