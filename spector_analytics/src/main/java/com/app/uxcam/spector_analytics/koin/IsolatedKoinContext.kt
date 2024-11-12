package com.app.uxcam.spector_analytics.koin

import android.content.Context
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

object IsolatedKoinContext {

    private var koinApp: KoinApplication? = null

    fun init(context: Context) {
        koinApp = koinApplication {
            modules(spectorModule)
        }.also {
            it.koin.declare(context)
        }
    }

    fun getKoin() : Koin {
        return koinApp!!.koin
    }
}