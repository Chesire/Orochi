package com.chesire.orochi.plugins.serialization

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import kotlinx.serialization.json.Json

/**
 * Installs and configures the [ContentNegotiation] plugin.
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}

/**
 * Provides an implementation of [Json] which has updated values.
 */
val Kson = Json {
    isLenient = true
    ignoreUnknownKeys = true
    encodeDefaults = true
    explicitNulls = false
}
