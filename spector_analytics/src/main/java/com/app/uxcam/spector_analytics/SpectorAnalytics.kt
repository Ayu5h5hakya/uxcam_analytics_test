package com.app.uxcam.spector_analytics

import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.koin.IsolatedKoinComponent
import kotlinx.coroutines.delay
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class SpectorAnalytics : IsolatedKoinComponent() {

    private val spectorRepository: SpectorRepository by inject()
    private val workManager: WorkManager by inject()

    init {
        val workRequest = PeriodicWorkRequestBuilder<AnalyticsWorker>(5, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun start() {
        spectorRepository.queueStartSession()
    }

    fun logEvent() {
        spectorRepository.queueTrack()
    }

    fun endSession() {
        spectorRepository.queueEndSession()
    }
}