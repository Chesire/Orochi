package com.chesire.orochi.routes.kitsu

import com.chesire.orochi.api.kitsu.model.KitsuAuthRequestDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthResponseDto
import com.chesire.orochi.routes.kitsu.model.KitsuInputAuthDto
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.request.receive
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.post
import io.ktor.routing.route

fun Routing.kitsu(client: HttpClient) {
    route("kitsu/") {
        post("auth/") {
            val input = call.receive<KitsuInputAuthDto>()
            val response = client.post<KitsuAuthResponseDto>("https://kitsu.io/api/oauth/token") {
                contentType(ContentType.Application.Json)
                body = KitsuAuthRequestDto(grantType = "password", username = input.username, password = input.password)
            }
            call.respondText("Success")
        }
    }
}
