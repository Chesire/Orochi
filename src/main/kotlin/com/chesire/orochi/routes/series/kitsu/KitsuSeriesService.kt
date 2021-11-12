package com.chesire.orochi.routes.series.kitsu

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.ContentType

class KitsuSeriesService(private val client: HttpClient) {

    suspend fun requestUserSeries(userId: String): String {
        val response = client.get<HttpResponse>(
            String.format(KitsuEndpoint.User.LibraryEntries, userId)
        ) {
            accept(ContentType.parse("application/vnd.api+json"))
        }

        println(response)
        return response.readText()
    }
}
