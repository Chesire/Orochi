package com.chesire.orochi.routes.series.kitsu

import com.chesire.orochi.api.kitsu.KitsuEndpoint
import com.chesire.orochi.api.kitsu.model.KitsuSeriesFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuSeriesSuccessDto
import com.chesire.orochi.ext.cast
import com.chesire.orochi.ext.isSuccessful
import com.chesire.orochi.routes.ResponseDomain
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.ContentType

class KitsuSeriesService(private val client: HttpClient) {

    suspend fun requestUserAnime(
        userId: String
    ): Result<ResponseDomain<KitsuSeriesSuccessDto>, ResponseDomain<KitsuSeriesFailureDto>> {
        val response = client.get<HttpResponse>(
            buildString {
                append(String.format(KitsuEndpoint.User.LibraryEntries, userId))
                append("?include=anime")
                append("&fields[libraryEntries]=status,progress,anime,startedAt,finishedAt,ratingTwenty")
                append("&fields[anime]=slug,canonicalTitle,startDate,endDate,subtype,status,posterImage,episodeCount")
                append("&filter[kind]=anime")
                append("&sort=anime.titles.canonical")
                append("&page[offset]=0") // TODO: Handle correct value here
                append("&page[limit]=500")
            }
        ) {
            accept(ContentType.parse("application/vnd.api+json"))
        }

        // TODO: Cycle over the remaining items till we get them all
        // TODO: Parse to correct model
        return if (response.isSuccessful) {
            Ok(ResponseDomain(response.status, response.cast()))
        } else {
            Err(ResponseDomain(response.status, response.cast()))
        }
    }

    // private suspend fun sendLibraryRequest(
    //     userId: String,
    //     offset: String
    // ): Result<ResponseDomain<String>, ResponseDomain<KitsuSeriesFailureDto>> {
//
    // }

    suspend fun requestUserManga(userId: String): String {
        val response = client.get<HttpResponse>(
            buildString {
                append(String.format(KitsuEndpoint.User.LibraryEntries, userId))
                append("?include=manga")
                append("&fields[libraryEntries]=status,progress,manga,startedAt,finishedAt,ratingTwenty")
                append("&fields[manga]=slug,canonicalTitle,startDate,endDate,subtype,status,posterImage,chapterCount")
                append("&filter[kind]=manga")
                append("&sort=manga.titles.canonical")
            }
        ) {
            accept(ContentType.parse("application/vnd.api+json"))
        }

        // TODO: Cycle over the remaining items till we get them all
        // TODO: Parse to correct model
        return response.readText()
    }
}
