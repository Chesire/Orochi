package com.chesire

import com.chesire.routing.configureRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

// Windows: Can be found at localhost:8080

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        port = 8080,
        host = "0.0.0.0"
    ) {
        configureRouting()
    }.start(wait = true)
}
