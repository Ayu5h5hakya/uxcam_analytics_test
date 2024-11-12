package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.room.SpectorEvent

interface SpectorRepository {

    fun queueStartSession()
    fun queueTrack()
    fun queueEndSession()

    fun startSession()
    fun track()
    fun endSession()

    fun getEventQueue() : List<SpectorEvent>
}