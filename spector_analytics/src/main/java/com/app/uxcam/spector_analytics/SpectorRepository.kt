package com.app.uxcam.spector_analytics

interface SpectorRepository {
    fun startSession()
    fun track()
    fun endSession()
}