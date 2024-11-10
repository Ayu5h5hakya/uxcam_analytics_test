package com.app.uxcam.analytics

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class AnalyticsWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val analyticsApi: AnalyticsApi,
) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        Log.i("analytics-worker", analyticsApi.toString())
        analyticsApi.sendAnalyticsData()
        return Result.success()
    }
}