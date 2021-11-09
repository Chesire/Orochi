package com.chesire.orochi.plugins.koin.modules

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import org.koin.dsl.module

/**
 * Provides an instance of the [HttpClient] as a [single].
 */
val httpClientModule = module {
    single {
        HttpClient() {
            install(JsonFeature)
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}
