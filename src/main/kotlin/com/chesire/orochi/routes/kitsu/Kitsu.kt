package com.chesire.orochi.routes.kitsu

import com.chesire.orochi.routes.kitsu.model.KitsuInputAuthDto
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.kitsu() {
    route("kitsu/") {
        post("auth/") {
            val input = call.receive<KitsuInputAuthDto>()
            call.respond(
                HttpStatusCode.OK,
                "Got input successfully - username ${input.username}"
            )
            // TODO: Forward on to Kitsu, and return the auth code back
        }
    }
}
