package com.app.uxcam.spector_analytics.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import org.koin.core.component.KoinComponent

class SpectorWorkerFactory : WorkerFactory(), KoinComponent {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): CoroutineWorker? {
        return try {
            val workerClass = Class.forName(workerClassName)
            val constructor = workerClass.getConstructor(Context::class.java, WorkerParameters::class.java)
            constructor.newInstance(appContext, workerParameters) as CoroutineWorker
        } catch (e : Exception) {
            null
        }
    }
}