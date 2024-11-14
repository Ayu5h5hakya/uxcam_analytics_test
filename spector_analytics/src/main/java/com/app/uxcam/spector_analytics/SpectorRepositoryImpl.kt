package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.room.SpectorDatabase
import com.app.uxcam.spector_analytics.room.SpectorEvent
import java.util.Date
import java.util.UUID

class SpectorRepositoryImpl(
    private val api: AnalyticsApi,
    private val db: SpectorDatabase
) : SpectorRepository {
    override suspend fun queueStartSession() {
        db.spectorDao().queue(
            SpectorEvent(
                UUID.randomUUID().toString(), Date().time, EventRepo.startEvent()
            )
        )
    }

    override suspend fun queueTrack() {
        db.spectorDao().queue(
            SpectorEvent(
                UUID.randomUUID().toString(), Date().time, EventRepo.trackEvent("click")
            )
        )
    }

    override suspend fun queueEndSession() {
        db.spectorDao().queue(
            SpectorEvent(
                UUID.randomUUID().toString(), Date().time, EventRepo.endEvent()
            )
        )
    }

    override suspend fun startSession() {

    }

    override suspend fun track() {

    }

    override suspend fun endSession() {

    }

    override suspend fun getEventQueue(): List<SpectorEvent> {
        return db.spectorDao().getAll()
    }
}