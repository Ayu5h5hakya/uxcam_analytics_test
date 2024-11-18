package com.app.uxcam.spector_analytics

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.app.uxcam.spector_analytics.data.datasources.local.SpectorDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpectorTestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SpectorTestApp)
        }
    }

    override fun onTerminate() {
        stopKoin()
        super.onTerminate()
    }
}