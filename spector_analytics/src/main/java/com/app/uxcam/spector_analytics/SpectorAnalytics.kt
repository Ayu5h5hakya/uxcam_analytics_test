package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.koin.IsolatedKoinComponent
import org.koin.core.component.inject

class SpectorAnalytics(context: Context) : IsolatedKoinComponent() {

    private val spectorRepository: SpectorRepository by inject()

    fun startSession() {
        spectorRepository.startSession()
    }

    fun logEvent() {

    }

    fun endSession() {

    }
}