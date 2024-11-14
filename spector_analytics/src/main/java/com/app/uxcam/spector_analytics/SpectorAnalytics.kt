package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.concurrent.TimeUnit

class SpectorAnalytics: KoinComponent  {

    private val spectorRepository: SpectorRepository by inject()
    private val workManager: WorkManager by inject()
      //private val context : Context by inject()

    init {
        //context.applicationContext
        val workRequest = PeriodicWorkRequestBuilder<AnalyticsWorker>(5, TimeUnit.MINUTES).build()
        workManager.enqueue(workRequest)
    }

    fun start() {

        //spectorRepository.queueStartSession()
    }

    fun logEvent() {
        //spectorRepository.queueTrack()
    }

    fun endSession() {
        //spectorRepository.queueEndSession()
    }
}