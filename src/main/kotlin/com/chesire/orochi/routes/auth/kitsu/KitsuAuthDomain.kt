package com.chesire.orochi.routes.auth.kitsu

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KitsuInputAuthDomain(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)

@Serializable
data class KitsuOutputAuthDomain(
    @SerialName("access_token")
    val accessToken: String
)
