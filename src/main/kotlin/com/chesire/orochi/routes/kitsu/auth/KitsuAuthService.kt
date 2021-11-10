package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthRequestDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import com.chesire.orochi.ext.cast
import com.chesire.orochi.ext.isSuccessful
import com.chesire.orochi.routes.ErrorDomain
import com.chesire.orochi.routes.ResponseDomain
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * Provides service to interact with Kitsu auth.
 */
class KitsuAuthService(private val client: HttpClient) {

    /**
     * Makes a request to get a new auth token from the Kitsu api.
     */
    suspend fun requestAuthToken(
        username: String,
        password: String
    ): Result<ResponseDomain<KitsuAuthSuccessDto>, ResponseDomain<KitsuAuthFailureDto>> {
        val response = client.post<HttpResponse>(KitsuEndpoint.OAuth.Token) {
            contentType(ContentType.Application.Json)
            body = KitsuAuthRequestDto(grantType = "password", username = username, password = password)
        }

        return if (response.isSuccessful) {
            Ok(ResponseDomain(response.status, response.cast()))
        } else {
            Err(ResponseDomain(response.status, response.cast()))
        }
    }

    /**
     * Creates an instance of [KitsuOutputAuthDomain] from a [KitsuAuthSuccessDto].
     */
    fun createKitsuOutputAuthDomain(successDto: KitsuAuthSuccessDto) = KitsuOutputAuthDomain(successDto.accessToken)

    /**
     * Creates an instance of [ErrorDomain] from a [KitsuAuthFailureDto].
     */
    fun createErrorDomain(errorDto: KitsuAuthFailureDto) = ErrorDomain(errorDto.error, errorDto.errorDescription)

}

