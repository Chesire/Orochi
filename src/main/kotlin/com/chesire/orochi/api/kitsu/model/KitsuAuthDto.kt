package com.chesire.orochi.api.kitsu.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KitsuAuthRequestDto(
    @SerialName("grant_type")
    val grantType: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)

// TODO: Use a sealed class for responses?

@Serializable
data class KitsuAuthResponseDto(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("expires_in")
    val expiresIn: Long,
    @SerialName("refresh_token")
    val refreshToken: String,
    @SerialName("scope")
    val scope: String,
    @SerialName("created_at")
    val createdAt: Long
)

@Serializable
data class KitsuAuthFailureResponseDto(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String
)
