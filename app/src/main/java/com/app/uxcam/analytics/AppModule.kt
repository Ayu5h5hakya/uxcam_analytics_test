package com.app.uxcam.analytics

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://eo5wfr96dnev8q2.m.pipedream.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAnalyticsSyncService(retrofit: Retrofit): AnalyticsApi {
        return retrofit.create(AnalyticsApi::class.java)
    }

}