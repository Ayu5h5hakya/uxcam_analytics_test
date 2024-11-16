package com.app.uxcam.analytics

import com.app.uxcam.spector_analytics.SpectorAnalytics
import org.koin.dsl.module

val demoModule = module {
    single { SpectorAnalytics() }
    single {
        DemoViewModel(get())
    }
}