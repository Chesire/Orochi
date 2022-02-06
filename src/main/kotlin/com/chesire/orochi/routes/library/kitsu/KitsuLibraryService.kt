package com.chesire.orochi.routes.library.kitsu

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient

/**
 * Provides service to interact with Kitsu library.
 */
class KitsuLibraryService(private val client: HttpClient) {

    suspend fun retrieveLibrary(authHeader: String?): Result<Unit, Unit> {
        return Ok(Unit)
    }
}
