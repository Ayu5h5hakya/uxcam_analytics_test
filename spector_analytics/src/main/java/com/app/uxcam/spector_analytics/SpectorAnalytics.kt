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

class SpectorAnalytics: KoinComponent  {

    private val spectorRepository: SpectorRepository by inject()
    private val workManager: WorkManager by inject()

    init {
        val workRequest = PeriodicWorkRequestBuilder<AnalyticsWorker>(5, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun start() {
        GlobalScope.launch {
            spectorRepository.queueStartSession()
        }
    }

    fun logEvent() {
        GlobalScope.launch {
            spectorRepository.queueTrack()
        }
    }

    fun endSession() {
        GlobalScope.launch {
            spectorRepository.queueEndSession()
        }
    }
}