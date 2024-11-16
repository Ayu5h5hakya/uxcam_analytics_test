package com.app.uxcam.spector_analytics

import com.app.uxcam.spector_analytics.datasources.remote.SpectorData
import retrofit2.http.Body
import retrofit2.http.POST

interface AnalyticsApi {
    @POST(".")
    suspend fun sendAnalyticsData(@Body data: SpectorData)
}