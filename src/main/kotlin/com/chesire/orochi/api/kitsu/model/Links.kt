package com.chesire.orochi.api.kitsu.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("first")
    val first: String,
    @SerialName("next")
    val next: String?,
    @SerialName("last")
    val last: String
)
