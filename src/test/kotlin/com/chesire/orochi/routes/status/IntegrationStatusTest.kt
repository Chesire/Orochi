package com.chesire.orochi.routes.status

import com.chesire.orochi.plugins.routing.configureRouting
import io.ktor.client.HttpClient
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.assertEquals
import org.junit.Test

class IntegrationStatusTest {

    @Test
    fun testStatus() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/status/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Status ok!", response.content)
            }
        }
    }
}
