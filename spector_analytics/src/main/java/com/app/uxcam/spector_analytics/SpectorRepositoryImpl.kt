package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.room.SpectorDatabase
import com.app.uxcam.spector_analytics.room.SpectorEvent
import java.util.Date
import java.util.UUID

class SpectorRepositoryImpl(
    private val api: AnalyticsApi,
    private val db: SpectorDatabase
) : SpectorRepository {
    override fun queueStartSession() {
//        db.spectorDao().queue(
//            SpectorEvent(
//                UUID.randomUUID().toString(), Date().time, EventRepo.startEvent()
//            )
//        )
    }

    override fun queueTrack() {
        db.spectorDao().queue(
            SpectorEvent(
                UUID.randomUUID().toString(), Date().time, EventRepo.trackEvent("click")
            )
        )
    }

    override fun queueEndSession() {
        db.spectorDao().queue(
            SpectorEvent(
                UUID.randomUUID().toString(), Date().time, EventRepo.endEvent()
            )
        )
    }

    override fun startSession() {

    }

    override fun track() {

    }

    override fun endSession() {

    }

    override fun getEventQueue(): List<SpectorEvent> {
        return listOf()
    }
}