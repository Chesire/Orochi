package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.routes.ErrorDomain
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

/**
 * Container route for all routes leading to kitsu/auth/.
 */
fun Route.kitsuAuth() {
    val service by inject<KitsuAuthService>()

    post("auth/") {
        val input = call.receive<KitsuInputAuthDomain>()
        val result = service.requestAuthToken(input.username, input.password)

        result
            .onSuccess {
                call.respond(
                    it.status,
                    KitsuOutputAuthDomain(it.model.accessToken)
                )
            }
            .onFailure {
                call.respond(
                    it.status,
                    ErrorDomain(it.model.error, it.model.errorDescription)
                )
            }
    }
}
