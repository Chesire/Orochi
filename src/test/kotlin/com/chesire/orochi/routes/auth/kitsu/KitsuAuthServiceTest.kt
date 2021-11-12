package com.chesire.orochi.routes.auth.kitsu

import com.chesire.orochi.util.MockRequest
import com.chesire.orochi.util.kitsuAuthFailureDto
import com.chesire.orochi.util.kitsuAuthSuccessDto
import com.chesire.orochi.util.setupMockedHttpClient
import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import io.ktor.http.HttpStatusCode
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KitsuAuthServiceTest {

    @Test
    fun `requestAuthToken, on success, returns ResponseDomain with KitsuAuthSuccessDto`() = runBlocking {
        val expectedCode = HttpStatusCode.OK
        val httpClient = setupMockedHttpClient(
            MockRequest(
                "/api/oauth/token",
                Json.encodeToString(kitsuAuthSuccessDto),
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
                Json.encodeToString(kitsuAuthFailureDto),
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
}
