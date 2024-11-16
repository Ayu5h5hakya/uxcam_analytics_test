package com.app.uxcam.spector_analytics.datasources.remote

import com.app.uxcam.spector_analytics.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.datasources.local.SpectorEvent

data class SpectorData(
    private val deviceContext: DeviceContext,
    private val queue : List<SpectorEvent>
)
