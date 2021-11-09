package com.chesire.orochi

import com.chesire.orochi.plugins.koin.configureKoin
import com.chesire.orochi.routes.startRouting
import com.chesire.orochi.plugins.serialization.configureSerialization
import io.ktor.application.Application

// Windows: Can be found at localhost:8080
fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    startRouting()
}
