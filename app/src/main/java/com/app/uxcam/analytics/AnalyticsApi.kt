package com.app.uxcam.analytics

import retrofit2.http.GET
import retrofit2.http.Query

interface AnalyticsApi {
    @GET(".")
    suspend fun sendAnalyticsData()
}