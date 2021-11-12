package com.chesire.orochi.routes.auth.kitsu

import com.chesire.orochi.plugins.koin.modules.defaultModules
import com.chesire.orochi.plugins.serialization.configureSerialization
import com.chesire.orochi.routes.startRouting
import com.chesire.orochi.util.MockRequest
import com.chesire.orochi.util.kitsuAuthSuccessDto
import com.chesire.orochi.util.kitsuInputAuthDomain
import com.chesire.orochi.util.setupMockedHttpClient
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.declare

class IntegrationKitsuAuthTest : KoinTest {
    /* TODO
        Write some integration tests here, need to:
        - Send bad credentials - get bad response
        - Send invalid (wrong typed) credentials - handle gracefully
        - Receive invalid data from api - handle gracefully
    */
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            defaultModules
        )
    }

    @Test
    fun `route auth-kitsu, on success, returns the KitsuOutputAuthDomain`() {
        declare {
            setupMockedHttpClient(
                MockRequest(
                    "/api/oauth/token",
                    Json.encodeToString(kitsuAuthSuccessDto),
                    HttpStatusCode.OK
                )
            )
        }

        withTestApplication({
            configureSerialization()
            startRouting()
        }) {
            handleRequest(HttpMethod.Post, "/auth/kitsu/") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(Json.encodeToString(kitsuInputAuthDomain))
            }.apply {
                val content = checkNotNull(response.content)
                val output = Json.decodeFromString<KitsuOutputAuthDomain>(content)

                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(kitsuAuthSuccessDto.accessToken, output.accessToken)
            }
        }
    }
}
