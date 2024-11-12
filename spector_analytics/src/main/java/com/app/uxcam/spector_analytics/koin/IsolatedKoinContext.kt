package com.app.uxcam.spector_analytics.koin

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.androidx.workmanager.koin.workManagerFactory

object IsolatedKoinContext {

    private var koinApp: KoinApplication? = null

    fun init(context: Context) {
        koinApp = koinApplication {
//            androidContext(context)
//            workManagerFactory()
            //modules(spectorModule)
        }
    }

    fun getKoin() : Koin {
        return koinApp!!.koin
    }
}