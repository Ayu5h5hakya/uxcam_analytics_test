package com.app.uxcam.analytics

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.uxcam.spector_analytics.SpectorAnalytics
import kotlinx.coroutines.launch

class DemoViewModel(private val analytics : SpectorAnalytics) : ViewModel() {

    fun startSession() {
        viewModelScope.launch {
            analytics.start()
        }
    }

    fun trackEvent(name : String, data : Map<String, String> = mapOf()) {
        viewModelScope.launch {
            analytics.logEvent(name, data)
        }
    }

    fun endSession() {
        viewModelScope.launch {
            analytics.endSession()
        }
    }

}