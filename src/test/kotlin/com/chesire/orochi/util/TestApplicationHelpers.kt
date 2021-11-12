package com.chesire.orochi.util

import com.chesire.orochi.plugins.serialization.configureSerialization
import com.chesire.orochi.plugins.statuspage.configureStatusPages
import com.chesire.orochi.routes.startRouting
import io.ktor.application.Application
import io.ktor.server.testing.TestApplicationEngine
import io.ktor.server.testing.withTestApplication

/**
 * Runs the [withTestApplication] method, providing a series of default parameters.
 * This is meant to replicate the behaviour of the production [Application] to ensure consistency.
 *
 * Pass anything into [moduleFunction] that is different to the production version.
 */
fun <R> withOrochiTestApp(
    moduleFunction: Application.() -> Unit = {},
    test: TestApplicationEngine.() -> R
) {
    withTestApplication({
        configureSerialization()
        configureStatusPages()
        startRouting()
        moduleFunction()
    }) {
        test()
    }
}
