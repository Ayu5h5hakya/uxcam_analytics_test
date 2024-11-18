package com.app.uxcam.spector_analytics

import androidx.work.Constraints
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.domain.repository.SpectorRepository
import com.app.uxcam.spector_analytics.worker.AnalyticsWorker
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class SpectorAnalytics : KoinComponent {

    private val spectorRepository: SpectorRepository by inject()
    private val workManager: WorkManager by inject()

    init {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val workRequest =
            PeriodicWorkRequestBuilder<AnalyticsWorker>(15, TimeUnit.MINUTES).setInitialDelay(
                5,
                TimeUnit.SECONDS
            )
                .setConstraints(constraints)
                .build()
        workManager.enqueue(workRequest)
    }

    /**
     * Add a start event item to the queue. [AnalyticsWorker] takes all queued up events from the db and syncs them
     * with the remote endpoint [Configuration.BASE_URL]
     */
    suspend fun start() {
        spectorRepository.queueStartSession()
    }

    /**
     * Add a log event item to the queue. [AnalyticsWorker] takes all queued up events from the db and syncs them
     * with the remote endpoint [Configuration.BASE_URL]
     *
     * @param name event name that will be visible in the remote end point logs
     * @param data optional event parameters
     */
    suspend fun logEvent(name: String, data: Map<String, String> = mapOf()) {
        spectorRepository.queueTrack(name, data)
    }

    /**
     * Add a end event item to the queue. [AnalyticsWorker] takes all queued up events from the db and syncs them
     * with the remote endpoint [Configuration.BASE_URL]
     */
    suspend fun endSession() {
        spectorRepository.queueEndSession()
    }
}