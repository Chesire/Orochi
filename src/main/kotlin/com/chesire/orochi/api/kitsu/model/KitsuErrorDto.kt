package com.chesire.orochi.api.kitsu.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * DTO object for errors from Kitsu.
 */
@Serializable
data class KitsuErrorDto(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String
)
