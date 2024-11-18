package com.app.uxcam.spector_analytics.data.datasources.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface AnalyticsApi {
    @POST(".")
    suspend fun sendAnalyticsData(@Body data: SpectorData)
}