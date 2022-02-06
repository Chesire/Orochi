package com.chesire.orochi.routes.auth.kitsu

import com.chesire.orochi.plugins.koin.modules.defaultModules
import com.chesire.orochi.routes.ErrorDomain
import com.chesire.orochi.util.MockRequest
import com.chesire.orochi.util.kitsuAuthSuccessDto
import com.chesire.orochi.util.kitsuErrorDto
import com.chesire.orochi.util.kitsuInputAuthDomain
import com.chesire.orochi.util.setupMockedHttpClient
import com.chesire.orochi.util.withOrochiTestApp
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Rule
import org.koin.test.AutoCloseKoinTest
import org.koin.test.KoinTestRule
import org.koin.test.mock.declare

class IntegrationKitsuAuthTest : AutoCloseKoinTest() {

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

        withOrochiTestApp {
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

    @Test
    fun `route auth-kitsu, on bad credentials, returns the ErrorDomain`() {
        declare {
            setupMockedHttpClient(
                MockRequest(
                    "/api/oauth/token",
                    Json.encodeToString(kitsuErrorDto),
                    HttpStatusCode.BadRequest
                )
            )
        }

        withOrochiTestApp {
            handleRequest(HttpMethod.Post, "/auth/kitsu/") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(Json.encodeToString(kitsuInputAuthDomain.copy(password = "wrong")))
            }.apply {
                val content = checkNotNull(response.content)
                val output = Json.decodeFromString<ErrorDomain>(content)

                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals(kitsuErrorDto.error, output.error)
                assertEquals(kitsuErrorDto.errorDescription, output.errorDescription)
            }
        }
    }

    @Test
    fun `route auth-kitsu, on invalid input data, returns the ErrorDomain`() {
        declare {
            setupMockedHttpClient(
                MockRequest(
                    "/api/oauth/token",
                    Json.encodeToString(kitsuAuthSuccessDto),
                    HttpStatusCode.OK
                )
            )
        }

        withOrochiTestApp {
            handleRequest(HttpMethod.Post, "/auth/kitsu/") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody("""{"username":"Orochi"}""")
            }.apply {
                val content = checkNotNull(response.content)
                val output = Json.decodeFromString<ErrorDomain>(content)

                assertEquals(HttpStatusCode.InternalServerError, response.status())
                assertEquals("internal error", output.error)
                assertTrue { output.errorDescription.contains("Unknown error has occurred") }
            }
        }
    }

    @Test
    fun `route auth-kitsu, on invalid api return data, returns the ErrorDomain`() {
        declare {
            setupMockedHttpClient(
                MockRequest(
                    "/api/oauth/token",
                    """{"error": "5", "invalid":"invalid"}""",
                    HttpStatusCode.BadRequest
                )
            )
        }

        withOrochiTestApp {
            handleRequest(HttpMethod.Post, "/auth/kitsu/") {
                addHeader("Content-Type", ContentType.Application.Json.toString())
                setBody(Json.encodeToString(kitsuInputAuthDomain.copy(password = "wrong")))
            }.apply {
                val content = checkNotNull(response.content)
                val output = Json.decodeFromString<ErrorDomain>(content)

                assertEquals(HttpStatusCode.InternalServerError, response.status())
                assertEquals("internal error", output.error)
                assertTrue { output.errorDescription.contains("Unknown error has occurred") }
            }
        }
    }
}
