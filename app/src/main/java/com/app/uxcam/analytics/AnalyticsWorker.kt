package com.app.uxcam.analytics

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AnalyticsWorker(context: Context, params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {
        return Result.success()
    }
}