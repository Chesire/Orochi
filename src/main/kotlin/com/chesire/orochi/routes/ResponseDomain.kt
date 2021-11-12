package com.chesire.orochi.routes

import io.ktor.http.HttpStatusCode

/**
 * Domain used when returning from service classes back into the route.
 */
data class ResponseDomain<T>(
    /**
     * The original [HttpStatusCode] of the response.
     */
    val status: HttpStatusCode,
    /**
     * The wrapped model parsed from the response.
     */
    val model: T
)
