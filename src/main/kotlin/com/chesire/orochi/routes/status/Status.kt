package com.chesire.orochi.routes.status

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.status() {
    get("status/") {
        call.respondText("Status ok!")
    }
}
