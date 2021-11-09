package com.chesire.orochi.plugins.koin

import com.chesire.orochi.plugins.koin.modules.httpClientModule
import io.ktor.application.Application
import io.ktor.application.install
import org.koin.ktor.ext.Koin

fun Application.configureKoin() {
    install(Koin) {
        modules(
            httpClientModule
        )
    }
}
