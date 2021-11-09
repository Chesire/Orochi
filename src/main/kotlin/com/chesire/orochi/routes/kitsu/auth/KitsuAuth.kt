package com.chesire.orochi.routes.kitsu.auth

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthRequestDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import com.chesire.orochi.ext.isSuccessful
import com.chesire.orochi.ext.parseAs
import com.chesire.orochi.routes.ErrorDomain
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
import org.koin.ktor.ext.inject

fun Route.kitsuAuth() {
    val client by inject<HttpClient>()

    post("auth/") {
        val input = call.receive<KitsuInputAuthDomain>()
        val response = client.post<HttpResponse>(KitsuEndpoint.OAuth.Token) {
            contentType(ContentType.Application.Json)
            body = KitsuAuthRequestDto(grantType = "password", username = input.username, password = input.password)
        }

        if (response.isSuccessful) {
            val parsedModel = response.parseAs<KitsuAuthSuccessDto>()
            call.respond(
                response.status,
                KitsuOutputAuthDomain(parsedModel.accessToken)
            )
        } else {
            val parsedModel = response.parseAs<KitsuAuthFailureDto>()
            call.respond(
                response.status,
                ErrorDomain(parsedModel.error, parsedModel.errorDescription)
            )
        }
    }
}
