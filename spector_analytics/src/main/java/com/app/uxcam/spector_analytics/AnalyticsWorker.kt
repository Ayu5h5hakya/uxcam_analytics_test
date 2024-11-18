package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.app.uxcam.spector_analytics.datasources.local.DeviceContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnalyticsWorker (
    context: Context,
    params: WorkerParameters,
) :
    CoroutineWorker(context, params), KoinComponent {

    private val spectorRepository: SpectorRepository by inject()
    private val deviceContext : DeviceContext by inject()

    override suspend fun doWork(): Result {
        try {
            spectorRepository.batchUpdate(deviceContext, spectorRepository.getEventQueue())
            return Result.success()
        } catch (exception : Exception) {
            return Result.failure()
        }
    }
}