package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.koin.IsolatedKoinComponent
import org.koin.core.component.inject

class SpectorAnalytics(context: Context) : IsolatedKoinComponent() {

    private var workManager: WorkManager = WorkManager.getInstance(context)
    private val spectorRepository: SpectorRepository by inject()

    fun startSession() {
        val workRequest = OneTimeWorkRequestBuilder<AnalyticsWorker>().build()
        workManager.enqueue(workRequest)
    }

    fun logEvent() {

    }

    fun endSession() {
        val workRequest = OneTimeWorkRequestBuilder<AnalyticsWorker>().build()
        workManager.enqueue(workRequest)
    }
}