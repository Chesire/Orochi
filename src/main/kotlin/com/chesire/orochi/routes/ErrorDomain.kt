package com.chesire.orochi.routes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorDomain(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String
)
