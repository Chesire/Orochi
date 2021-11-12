package com.chesire.orochi.routes.status

import com.chesire.orochi.util.withOrochiTestApp
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegrationStatusTest {

    @Test
    fun `route status, returns status OK`() {
        withOrochiTestApp {
            handleRequest(HttpMethod.Get, "/status/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Status ok!", response.content)
            }
        }
    }
}
