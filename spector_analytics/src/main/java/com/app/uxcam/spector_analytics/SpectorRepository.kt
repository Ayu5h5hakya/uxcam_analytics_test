package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.datasources.local.SpectorEvent

interface SpectorRepository {

    suspend fun queueStartSession()
    suspend fun queueTrack(name : String,data : Map<String, String>)
    suspend fun queueEndSession()

    suspend fun batchUpdate(deviceContext: DeviceContext, queue : List<SpectorEvent>)

    suspend fun getEventQueue() : List<SpectorEvent>
}