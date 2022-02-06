package com.chesire.orochi.routes.library.kitsu

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import com.chesire.orochi.common.Headers
import com.chesire.orochi.ext.isSuccessful
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

/**
 * Provides service to interact with Kitsu library.
 */
class KitsuLibraryService(private val client: HttpClient) {

    suspend fun retrieveLibrary(
        bearerToken: String?,
        userId: String?
    ): Result<Unit, Unit> {
        return if (bearerToken == null) {
            Err(Unit)
        } else if (userId == null) {
            Err(Unit)
        } else {
            // TODO: Introduce builder for the URL, since we need various filters such as userId
            val response = client.get<HttpResponse>(buildKitsuUrl(userId)) {
                accept(ContentType.parse(Headers.VND_API_JSON))
                contentType(ContentType.parse(Headers.VND_API_JSON))
                header(Headers.AUTHORIZATION, "Bearer $bearerToken")
            }

            if (response.isSuccessful) {
                Ok(Unit)
            } else {
                Err(Unit)
            }
        }
    }

    private fun buildKitsuUrl(userId: String): String {
        return "${KitsuEndpoint.Library.Entries}/filter[userId]=$userId"
    }
}
