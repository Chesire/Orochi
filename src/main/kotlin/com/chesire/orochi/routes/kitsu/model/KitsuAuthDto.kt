package com.chesire.orochi.routes.kitsu.model

import kotlinx.serialization.Serializable

@Serializable
data class KitsuInputAuthDto(
    val username: String,
    val password: String
)

@Serializable
data class KitsuAuthRequestDto(
    val username: String,
    val password: String
)

@Serializable
data class KitsuAuthResponseDto(
    val accessToken: String
)
