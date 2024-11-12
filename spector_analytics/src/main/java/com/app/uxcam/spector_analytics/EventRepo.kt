package com.app.uxcam.spector_analytics

object EventRepo {
    fun startEvent() = "start_session"
    fun trackEvent(name : String) = "track_$name"
    fun endEvent() = "end_session"
}