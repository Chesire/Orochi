package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import com.chesire.orochi.util.MockRequest
import com.chesire.orochi.util.setupMockedHttpClient
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import io.ktor.http.HttpStatusCode
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking

class KitsuAuthServiceTest {

    @Test
    fun `requestAuthToken, on success, returns ResponseDomain with KitsuAuthSuccessDto`() = runBlocking {
        val expectedCode = HttpStatusCode.OK
        val httpClient = setupMockedHttpClient(
            MockRequest(
                "/api/oauth/token",
                ValidRequestJson,
                expectedCode
            )
        )
        val service = KitsuAuthService(httpClient)

        val result = service.requestAuthToken("username", "password").get()

        checkNotNull(result)
        assertEquals(expectedCode, result.status)
    }

    @Test
    fun `requestAuthToken, on failure, returns ResponseDomain with KitsuAuthFailureDto`() = runBlocking {
        val expectedCode = HttpStatusCode.BadRequest
        val httpClient = setupMockedHttpClient(
            MockRequest(
                "/api/oauth/token",
                InvalidRequestJson,
                expectedCode
            )
        )
        val service = KitsuAuthService(httpClient)

        val result = service.requestAuthToken("username", "password").getError()

        checkNotNull(result)
        assertEquals(expectedCode, result.status)
    }

    @Test
    fun `createKitsuOutputAuthDomain returns new model`() {
        val dto = kitsuAuthSuccessDto

        val result = KitsuAuthService(mockk()).createKitsuOutputAuthDomain(dto)

        assertEquals(dto.accessToken, result.accessToken)
    }

    @Test
    fun `createErrorDomain returns new model`() {
        val dto = kitsuAuthFailureDto

        val result = KitsuAuthService(mockk()).createErrorDomain(dto)

        assertEquals(dto.error, result.error)
        assertEquals(dto.errorDescription, result.errorDescription)
    }

    companion object {
        private const val ValidRequestJson =
            """{"access_token":"accessToken","token_type":"Bearer","expires_in":423082,"refresh_token":"refreshToken","scope":"public","created_at":1634401310}"""
        private const val InvalidRequestJson =
            """{"error": "invalid_grant", "error_description": "The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client."}"""

        private val kitsuAuthSuccessDto: KitsuAuthSuccessDto
            get() = KitsuAuthSuccessDto(
                accessToken = "accessToken",
                tokenType = "tokenType",
                expiresIn = 20L,
                refreshToken = "refreshToken",
                scope = "scope",
                createdAt = 1L
            )

        private val kitsuAuthFailureDto: KitsuAuthFailureDto
            get() = KitsuAuthFailureDto(
                error = "error",
                errorDescription = "error_description"
            )
    }
}
