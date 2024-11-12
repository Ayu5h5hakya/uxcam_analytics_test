package com.app.uxcam.spector_analytics.koin

import com.app.uxcam.spector_analytics.AnalyticsApi
import com.app.uxcam.spector_analytics.SpectorRepository
import com.app.uxcam.spector_analytics.SpectorRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val spectorModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://eo5wfr96dnev8q2.m.pipedream.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnalyticsApi::class.java)
    }
    single<SpectorRepository> {
        SpectorRepositoryImpl(get())
    }
}