package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthRequestDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import com.chesire.orochi.ext.isSuccessful
import com.chesire.orochi.ext.parseAs
import io.ktor.application.call
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post

fun Route.kitsuAuth(client: HttpClient) {
    post("auth/") {
        val input = call.receive<KitsuInputAuthDto>()
        val response = client.post<HttpResponse>(KitsuEndpoint.OAuth.Token) {
            contentType(ContentType.Application.Json)
            body = KitsuAuthRequestDto(grantType = "password", username = input.username, password = input.password)
        }

        call.respond(
            response.status,
            if (response.isSuccessful) response.parseAs<KitsuAuthSuccessDto>() else response.parseAs<KitsuAuthFailureDto>()
        )
    }
}
