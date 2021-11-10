package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import io.ktor.client.HttpClient
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class KitsuAuthServiceTest {

    private lateinit var httpClient: HttpClient
    private lateinit var service: KitsuAuthService

    @BeforeTest
    fun before() {
        httpClient = mockk()
        service = KitsuAuthService(httpClient)
    }

    @Test
    fun `requestAuthToken, on success, returns ResponseDomain with KitsuAuthSuccessDto`() {

    }

    @Test
    fun `requestAuthToken, on failure, returns ResponseDomain with KitsuAuthFailureDto`() {

    }

    @Test
    fun `createKitsuOutputAuthDomain returns new model`() {
        val dto = kitsuAuthSuccessDto

        val result = service.createKitsuOutputAuthDomain(dto)

        assertEquals(dto.accessToken, result.accessToken)
    }

    @Test
    fun `createErrorDomain returns new model`() {
        val dto = kitsuAuthFailureDto

        val result = service.createErrorDomain(dto)

        assertEquals(dto.error, result.error)
        assertEquals(dto.errorDescription, result.errorDescription)
    }

    companion object {
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
