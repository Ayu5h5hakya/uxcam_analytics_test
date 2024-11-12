package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class AnalyticsWorker (
    context: Context,
    params: WorkerParameters,
    private val analyticsApi: AnalyticsApi,
) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        analyticsApi.sendAnalyticsData()
        return Result.success()
    }
}