package com.chesire.orochi.routes.kitsu

import com.chesire.orochi.routes.kitsu.auth.kitsuAuth
import io.ktor.client.HttpClient
import io.ktor.routing.Routing
import io.ktor.routing.route

fun Routing.kitsu() {
    route("kitsu/") {
        kitsuAuth()
    }
}
