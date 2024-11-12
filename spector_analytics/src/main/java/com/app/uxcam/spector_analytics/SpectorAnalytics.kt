package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class SpectorAnalytics(context: Context){

    private var workManager: WorkManager = WorkManager.getInstance(context)

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