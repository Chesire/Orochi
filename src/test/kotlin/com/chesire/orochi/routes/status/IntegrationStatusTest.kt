package com.chesire.orochi.routes.status

import com.chesire.orochi.routes.startRouting
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.assertEquals
import org.junit.Test

class IntegrationStatusTest {

    @Test
    fun testStatus() {
        withTestApplication({ startRouting() }) {
            handleRequest(HttpMethod.Get, "/status/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Status ok!", response.content)
            }
        }
    }
}
