package com.app.uxcam.spector_analytics.koin

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

abstract class IsolatedKoinComponent : KoinComponent {
    override fun getKoin(): Koin = IsolatedKoinContext.koin
}