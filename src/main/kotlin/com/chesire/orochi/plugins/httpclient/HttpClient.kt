package com.chesire.orochi.plugins.httpclient

import io.ktor.application.Application
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging

fun Application.configureHttpClient(): HttpClient {
    return HttpClient() {
        install(JsonFeature)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}
