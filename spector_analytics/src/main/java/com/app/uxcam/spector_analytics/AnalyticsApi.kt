package com.app.uxcam.spector_analytics

import retrofit2.http.GET

interface AnalyticsApi {
    @GET(".")
    suspend fun sendAnalyticsData()
}