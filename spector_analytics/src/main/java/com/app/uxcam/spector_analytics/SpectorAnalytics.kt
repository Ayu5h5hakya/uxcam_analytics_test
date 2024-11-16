package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class SpectorAnalytics : KoinComponent {

    private val spectorRepository: SpectorRepository by inject()
    private val workManager: WorkManager by inject()

    init {
        val workRequest =
            PeriodicWorkRequestBuilder<AnalyticsWorker>(10, TimeUnit.SECONDS).setInitialDelay(
                5,
                TimeUnit.SECONDS
            ).build()
        workManager.enqueue(workRequest)
    }

    suspend fun start() {
        spectorRepository.queueStartSession()
    }

    suspend fun logEvent(name: String, data: Map<String, String>) {
        spectorRepository.queueTrack(name, data)
    }

    suspend fun endSession() {
        spectorRepository.queueEndSession()
    }
}