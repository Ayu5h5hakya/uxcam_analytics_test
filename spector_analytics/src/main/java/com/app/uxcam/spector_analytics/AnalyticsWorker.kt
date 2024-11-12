package com.app.uxcam.spector_analytics

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AnalyticsWorker (
    context: Context,
    params: WorkerParameters,
) :
    CoroutineWorker(context, params), KoinComponent {

    private val spectorRepository: SpectorRepository by inject()

    override suspend fun doWork(): Result {
        val pendingEvents = spectorRepository.getEventQueue()
        Log.i("analytics-worker", "doWork: ")
        return Result.success()
    }
}