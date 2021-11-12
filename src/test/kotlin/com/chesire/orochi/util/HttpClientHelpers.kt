package com.chesire.orochi.util

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

/**
 * Sets up an instance of [HttpClient] that uses a [MockEngine].
 */
fun setupMockedHttpClient(vararg mockedRequests: MockRequest): HttpClient {
    // We have to use this for unit tests as well, because we cannot mockk the reified `request` and `post` functions.
    return HttpClient(MockEngine) {
        install(JsonFeature)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        expectSuccess = false
        engine {
            addHandler { request ->
                mockedRequests
                    .find { request.url.encodedPath == it.requestEncodedPath }
                    ?.let {
                        respond(
                            it.responseJson,
                            it.responseStatusCode,
                            it.responseHeaders
                        )
                    } ?: error("Could not find configuration for $request")
            }
        }
    }
}

/**
 * Request data to mock on the [MockEngine], to be used with [setupMockedHttpClient].
 */
data class MockRequest(
    val requestEncodedPath: String,
    val responseJson: String,
    val responseStatusCode: HttpStatusCode,
    val responseHeaders: Headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
)
