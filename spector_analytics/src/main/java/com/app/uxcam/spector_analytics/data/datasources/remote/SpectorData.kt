package com.app.uxcam.spector_analytics.data.datasources.remote

import com.app.uxcam.spector_analytics.data.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.data.datasources.local.SpectorEvent
/**
 * Data class for [SpectorData] object for combining current [DeviceContext] and all queued up [SpectorEvent]
 */
data class SpectorData(
    private val deviceContext: DeviceContext,
    private val queue : List<SpectorEvent>
)
