package com.chesire.orochi.ext

import io.ktor.request.ApplicationRequest
import io.ktor.request.header

private const val KITSU_AUTH_HEADER = "kitsu_auth_token"

/**
 * Retrieves the string for the Kitsu access token from the [ApplicationRequest].
 */
val ApplicationRequest.kitsuAuthHeader: String?
    get() = header(KITSU_AUTH_HEADER)
