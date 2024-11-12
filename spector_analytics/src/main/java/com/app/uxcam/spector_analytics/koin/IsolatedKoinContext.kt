package com.app.uxcam.spector_analytics.koin

import org.koin.dsl.koinApplication

object IsolatedKoinContext {

    private val koinApp = koinApplication {
        modules(spectorModule)
    }

    val koin = koinApp.koin
}