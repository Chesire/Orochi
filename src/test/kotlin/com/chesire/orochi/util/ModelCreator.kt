package com.chesire.orochi.util

import com.chesire.orochi.api.kitsu.model.KitsuAuthFailureDto
import com.chesire.orochi.api.kitsu.model.KitsuAuthSuccessDto
import com.chesire.orochi.routes.auth.kitsu.KitsuInputAuthDomain

val kitsuInputAuthDomain
    get() = KitsuInputAuthDomain(
        username = "OrochiFake",
        password = "Password"
    )

val kitsuAuthSuccessDto
    get() = KitsuAuthSuccessDto(
        accessToken = "fakeAccessToken",
        tokenType = "Bearer",
        expiresIn = 423082,
        refreshToken = "fakeRefreshToken",
        scope = "public",
        createdAt = 1634401310
    )

val kitsuAuthFailureDto
    get() = KitsuAuthFailureDto(
        error = "invalid_grant",
        errorDescription = "The provided authorization grant is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client."
    )
