package com.chesire.orochi

import com.chesire.orochi.plugins.koin.configureKoin
import com.chesire.orochi.plugins.serialization.configureSerialization
import com.chesire.orochi.routes.startRouting
import io.ktor.application.Application

/**
 * Kotlin main method - starts the Ktor engine.
 * After running in IDEA this should be available at localhost:8080.
 */
fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

/**
 * Ktor [module], will configure the ktor client.
 */
@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    startRouting()
}
