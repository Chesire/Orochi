package com.chesire.orochi.plugins.koin

import com.chesire.orochi.plugins.koin.modules.httpClientModule
import kotlin.test.Test
import org.koin.test.KoinTest
import org.koin.test.check.checkKoinModules

class KoinModulesTest : KoinTest {

    @Test
    fun `verify Koin modules`() {
        checkKoinModules(httpClientModule)
    }
}
