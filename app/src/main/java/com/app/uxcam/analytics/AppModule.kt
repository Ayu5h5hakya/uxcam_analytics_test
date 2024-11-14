package com.app.uxcam.analytics

import com.app.uxcam.spector_analytics.SpectorAnalytics
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val demoModule = module {
    single { SpectorAnalytics() }
    viewModelOf(::DemoViewModel)
}