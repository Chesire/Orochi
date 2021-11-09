package com.chesire.orochi.ext

import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

val HttpResponse.isSuccessful: Boolean
    get() = status == HttpStatusCode.OK ||
        status == HttpStatusCode.Created ||
        status == HttpStatusCode.Accepted

suspend inline fun <reified T> HttpResponse.parseAs(): T {
    return Json.decodeFromString(readText())
}
