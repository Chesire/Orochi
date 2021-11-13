package com.chesire.orochi.api.kitsu.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("tiny")
    val tiny: String,
    @SerialName("small")
    val small: String,
    @SerialName("medium")
    val medium: String,
    @SerialName("large")
    val large: String,
    @SerialName("original")
    val original: String,
    @SerialName("meta")
    val meta: Meta
) {

    @Serializable
    data class Meta(
        @SerialName("dimensions")
        val dimensions: Dimensions
    ) {

        @Serializable
        data class Dimensions(
            @SerialName("tiny")
            val tiny: Dimension,
            @SerialName("small")
            val small: Dimension,
            @SerialName("medium")
            val medium: Dimension,
            @SerialName("large")
            val large: Dimension
        ) {

            @Serializable
            data class Dimension(
                @SerialName("width")
                val width: Int?,
                @SerialName("height")
                val height: Int?
            )
        }
    }
}
