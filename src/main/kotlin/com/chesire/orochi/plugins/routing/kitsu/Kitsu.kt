package com.chesire.orochi.plugins.routing.kitsu

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.kitsu() {
    route("kitsu/") {
        post("auth/") {
            call.respondText("Status ok!")
        }
    }
}
