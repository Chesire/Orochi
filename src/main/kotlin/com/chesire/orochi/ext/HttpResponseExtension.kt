package com.chesire.orochi.ext

import com.chesire.orochi.plugins.serialization.Kson
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.decodeFromString

/**
 * Checks if this [HttpResponse] was a success status code.
 */
val HttpResponse.isSuccessful: Boolean
    get() = status == HttpStatusCode.OK ||
        status == HttpStatusCode.Created ||
        status == HttpStatusCode.Accepted

/**
 * Attempts to cast the content within this [HttpResponse] into an instance of [T].
 */
suspend inline fun <reified T> HttpResponse.cast(): T {
    return Kson.decodeFromString(readText())
}
