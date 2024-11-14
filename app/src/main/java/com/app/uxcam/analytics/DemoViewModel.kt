package com.app.uxcam.analytics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.uxcam.spector_analytics.SpectorAnalytics
import kotlinx.coroutines.launch

class DemoViewModel(private val analytics : SpectorAnalytics) : ViewModel() {

    init {
        analytics.start()
    }

    fun startSession() {
        viewModelScope.launch {
            analytics.start()
        }
    }

    fun trackEvent() {
        viewModelScope.launch {
            analytics.logEvent()
        }
    }

    fun endSession() {
        viewModelScope.launch {
            analytics.endSession()
        }
    }

}