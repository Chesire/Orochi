package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.model.KitsuAuthRequestDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthResponseDto
import com.chesire.orochi.api.kitsu.KitsuEndpoint
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.kitsuAuth(client: HttpClient) {
    post("auth/") {
        val input = call.receive<KitsuInputAuthDto>()
        val response = client.post<KitsuAuthResponseDto>(KitsuEndpoint.OAuth.Token) {
            contentType(ContentType.Application.Json)
            body = KitsuAuthRequestDto(grantType = "password", username = input.username, password = input.password)
        }
        call.respondText("Success")
    }
}
