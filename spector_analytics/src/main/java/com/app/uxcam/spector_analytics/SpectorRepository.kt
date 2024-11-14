package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.room.SpectorEvent

interface SpectorRepository {

    suspend fun queueStartSession()
    suspend fun queueTrack()
    suspend fun queueEndSession()

    suspend fun startSession()
    suspend fun track()
    suspend fun endSession()

    suspend fun getEventQueue() : List<SpectorEvent>
}