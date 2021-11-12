package com.chesire.orochi.routes.auth.kitsu

import com.chesire.orochi.plugins.koin.modules.defaultModules
import com.chesire.orochi.plugins.serialization.configureSerialization
import com.chesire.orochi.routes.startRouting
import com.chesire.orochi.util.MockRequest
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
        - Send good credentials - get good response
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
                    ValidRequestJson,
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
                setBody(
                    Json.encodeToString(
                        KitsuInputAuthDomain(
                            username = "OrochiTest",
                            password = "foobar"
                        )
                    )
                )
            }.apply {
                val output = Json.decodeFromString<KitsuOutputAuthDomain>(response.content!!)

                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("accessToken", output.accessToken)
            }
        }
    }
}

private const val ValidRequestJson =
    """{"access_token":"accessToken","token_type":"Bearer","expires_in":423082,"refresh_token":"refreshToken","scope":"public","created_at":1634401310}"""
