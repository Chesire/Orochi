package com.chesire.orochi.routes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Domain for any errors that come out from Orochi.
 */
@Serializable
data class ErrorDomain(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String
)
