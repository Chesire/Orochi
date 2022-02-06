package com.chesire.orochi.ext

import io.ktor.request.ApplicationRequest
import io.ktor.request.header

private const val KITSU_BEARER_TOKEN = "kitsu_token"
private const val KITSU_USER_ID = "kitsu_user_id"

/**
 * Retrieves the Kitsu access token from the [ApplicationRequest] headers.
 */
val ApplicationRequest.kitsuBearerToken: String?
    get() = header(KITSU_BEARER_TOKEN)

/**
 * Retrieves the Kitsu user id from the [ApplicationRequest] headers.
 */
val ApplicationRequest.kitsuUserId: String?
    get() = header(KITSU_USER_ID)
