package com.app.uxcam.spector_analytics.data.datasources.local

data class DeviceContext(
    private val model : String,
    private val sdk : Int,
    private val manufacturer : String,
    private val brand : String,
)