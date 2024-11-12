package com.app.uxcam.analytics

import android.app.Application
import com.app.uxcam.spector_analytics.koin.IsolatedKoinContext

class AnalyticsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        IsolatedKoinContext.init(this@AnalyticsApplication)
    }
}