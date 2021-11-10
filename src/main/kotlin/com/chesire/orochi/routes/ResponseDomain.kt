package com.chesire.orochi.routes

import io.ktor.http.HttpStatusCode

data class ResponseDomain<T>(
    val status: HttpStatusCode,
    val model: T
)
