package com.app.uxcam.analytics

import android.app.Application
import com.app.uxcam.spector_analytics.koin.spectorModule
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class AnalyticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AnalyticsApplication)
            workManagerFactory()
            modules(spectorModule)
            modules(demoModule)
        }
    }
}