package com.app.uxcam.spector_analytics.data.datasources.local

/**
 * Data class for a [DeviceContext] object used to aggregate device meta data. Useful for user segmentation
 */
data class DeviceContext(
    private val model : String,
    private val sdk : Int,
    private val manufacturer : String,
    private val brand : String,
)