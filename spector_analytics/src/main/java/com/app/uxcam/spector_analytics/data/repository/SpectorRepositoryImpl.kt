package com.app.uxcam.spector_analytics.data.repository

import com.app.uxcam.spector_analytics.data.datasources.remote.AnalyticsApi
import com.app.uxcam.spector_analytics.domain.repository.SpectorRepository
import com.app.uxcam.spector_analytics.data.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.data.datasources.local.SessionData
import com.app.uxcam.spector_analytics.data.datasources.local.SpectorDatabase
import com.app.uxcam.spector_analytics.data.datasources.local.SpectorEvent
import com.app.uxcam.spector_analytics.data.datasources.remote.SpectorData
import java.util.Date

class SpectorRepositoryImpl(
    private val api: AnalyticsApi,
    private val db: SpectorDatabase
) : SpectorRepository {

    private suspend fun getSession(currentTimeStamp: Long): SessionData {
        val sessionAlreadyExists = db.sessionDao().getAll().isNotEmpty()
        if (sessionAlreadyExists) {
            val session = db.sessionDao().getLatestSessionData()
            val startTimeStamp = session.timeStamp
            if (currentTimeStamp - startTimeStamp < 10000L) {
                //existing session is valid, reuse it
                return session
            } else {
                //existing session has timed out, create a new one
                val data = SessionData(
                    timeStamp = currentTimeStamp,
                    sessionNumber = session.sessionNumber + 1
                )
                db.sessionDao().add(data)
                return data
            }
        } else {
            val data = SessionData(timeStamp = currentTimeStamp, sessionNumber = 1)
            db.sessionDao().add(data)
            return data
        }
    }

    override suspend fun queueStartSession() {
        val currentTimeStamp = Date().time
        val session = getSession(currentTimeStamp)
        db.spectorDao().queue(
            SpectorEvent.start(
                sessionId = session.sessionNumber,
                timeStamp = currentTimeStamp,
            )
        )
    }

    override suspend fun queueTrack(name: String, data: Map<String, String>) {
        val currentTimeStamp = Date().time
        val session = getSession(currentTimeStamp)
        db.sessionDao().update(session.sessionNumber, currentTimeStamp)
        db.spectorDao().queue(
            SpectorEvent(
                sessionId = session.sessionNumber,
                timeStamp = currentTimeStamp,
                name = "track_$name",
                data = data
            )
        )
    }

    override suspend fun queueEndSession() {
        val currentTimeStamp = Date().time
        val session = getSession(currentTimeStamp)
        db.sessionDao().delete(session.sessionNumber)
        db.spectorDao().queue(
            SpectorEvent.end(
                sessionId = session.sessionNumber,
                timeStamp = currentTimeStamp,
            )
        )
    }

    override suspend fun batchUpdate(deviceContext: DeviceContext, queue: List<SpectorEvent>) {
        api.sendAnalyticsData(SpectorData(deviceContext, queue))
        db.sessionDao().deleteAll()
    }

    override suspend fun getEventQueue(): List<SpectorEvent> {
        return db.spectorDao().getAll()
    }
}