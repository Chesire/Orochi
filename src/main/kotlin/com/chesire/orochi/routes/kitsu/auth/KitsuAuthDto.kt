package com.chesire.orochi.routes.kitsu.auth

import kotlinx.serialization.Serializable

@Serializable
data class KitsuInputAuthDto(
    val username: String,
    val password: String
)
