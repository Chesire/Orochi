package com.chesire.orochi.plugins.koin.modules

import com.chesire.orochi.routes.auth.kitsu.KitsuAuthService
import com.chesire.orochi.routes.library.kitsu.KitsuLibraryService
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import org.koin.dsl.module

/**
 * Provides an instance of the [HttpClient] as a [single].
 */
val httpClientModule = module {
    single {
        HttpClient() {
            install(JsonFeature)
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            expectSuccess = false
        }
    }
}

/**
 * Provides instances for the auth package.
 */
val authModule = module {
    single {
        KitsuAuthService(get())
    }
}

/**
 * Provides instances for the library package.
 */
val libraryModule = module {
    single {
        KitsuLibraryService(get())
    }
}

/**
 * Collection of all Koin modules.
 */
val defaultModules = listOf(
    httpClientModule,
    authModule,
    libraryModule
)
