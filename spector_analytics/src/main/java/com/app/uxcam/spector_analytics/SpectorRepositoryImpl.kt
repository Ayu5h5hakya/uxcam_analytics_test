package com.app.uxcam.spector_analytics

import androidx.room.RoomDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class SpectorRepositoryImpl(
    private val workManager: WorkManager,
    private val api: AnalyticsApi,
    private val db : RoomDatabase
) : SpectorRepository {
    override fun startSession() {
        val workRequest = OneTimeWorkRequestBuilder<AnalyticsWorker>().build()
        workManager.enqueue(workRequest)
    }

    override fun track() {
        TODO("Not yet implemented")
    }

    override fun endSession() {
        val workRequest = OneTimeWorkRequestBuilder<AnalyticsWorker>().build()
        workManager.enqueue(workRequest)
    }
}