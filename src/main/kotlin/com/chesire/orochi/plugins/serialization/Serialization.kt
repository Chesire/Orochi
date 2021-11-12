package com.chesire.orochi.plugins.serialization

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json

/**
 * Installs and configures the [ContentNegotiation] plugin.
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
