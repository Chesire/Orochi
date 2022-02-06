package com.chesire.orochi.routes.library.kitsu

import com.chesire.orochi.ext.kitsuBearerToken
import com.chesire.orochi.ext.kitsuUserId
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.application.call
import io.ktor.routing.Route
import io.ktor.routing.get
import org.koin.ktor.ext.inject

/**
 * Container route for all routes leading to library/kitsu/.
 */
fun Route.kitsuLibrary() {
    val service by inject<KitsuLibraryService>()

    get("kitsu/") {
        service.retrieveLibrary(call.request.kitsuBearerToken, call.request.kitsuUserId)
            .onSuccess {
                // Parse into useful data and return?
            }
            .onFailure {
                // Parse into useful data object
            }
    }
}
