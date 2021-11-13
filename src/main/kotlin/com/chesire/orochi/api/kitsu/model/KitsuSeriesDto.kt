package com.chesire.orochi.api.kitsu.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KitsuSeriesSuccessDto(
    @SerialName("data")
    val data: List<KitsuSeriesDataDto>,
    @SerialName("included")
    val included: List<KitsuSeriesIncludedDto>,
    @SerialName("links")
    val links: Links
) {

    @Serializable
    data class KitsuSeriesDataDto(
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String,
        @SerialName("attributes")
        val attributes: Attributes,
        @SerialName("relationships")
        val relationships: Relationships
    ) {

        @Serializable
        data class Attributes(
            @SerialName("status")
            val status: String,
            @SerialName("progress")
            val progress: Int,
            @SerialName("ratingTwenty")
            val rating: Int?,
            @SerialName("startedAt")
            val startedAt: String?,
            @SerialName("finishedAt")
            val finishedAt: String?
        )

        @Serializable
        data class Relationships(
            @SerialName("anime")
            val anime: RelationshipObject? = null,
            @SerialName("manga")
            val manga: RelationshipObject? = null
        ) {

            @Serializable
            data class RelationshipObject(
                @SerialName("data")
                val data: RelationshipData? = null
            ) {

                @Serializable
                data class RelationshipData(
                    @SerialName("id")
                    val id: Int
                )
            }
        }
    }

    @Serializable
    data class KitsuSeriesIncludedDto(
        @SerialName("id")
        val id: Int,
        @SerialName("type")
        val type: String,
        @SerialName("attributes")
        val attributes: Attributes
    ) {

        @Serializable
        data class Attributes(
            @SerialName("slug")
            val slug: String,
            @SerialName("canonicalTitle")
            val canonicalTitle: String,
            @SerialName("startDate")
            val startDate: String?,
            @SerialName("endDate")
            val endDate: String?,
            @SerialName("subtype")
            val subtype: String,
            @SerialName("status")
            val status: String,
            @SerialName("posterImage")
            val posterImage: Image?,
            @SerialName("chapterCount")
            val chapterCount: Int?,
            @SerialName("episodeCount")
            val episodeCount: Int?
        )
    }
}

@Serializable
data class KitsuSeriesFailureDto(
    @SerialName("errors")
    val errors: List<KitsuSeriesFailureDetailsDto>
) {

    @Serializable
    data class KitsuSeriesFailureDetailsDto(
        @SerialName("title")
        val title: String,
        @SerialName("detail")
        val detail: String,
        @SerialName("code")
        val code: String,
        @SerialName("status")
        val status: String,
        @SerialName("source")
        val source: KitsuSeriesFailureSourceDto
    ) {

        @Serializable
        data class KitsuSeriesFailureSourceDto(
            @SerialName("pointer")
            val pointer: String,
            @SerialName("parameter")
            val parameter: String
        )
    }
}
