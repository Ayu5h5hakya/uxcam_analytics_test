package com.app.uxcam.spector_analytics.koin

import android.os.Build
import androidx.room.Room
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.Configuration
import com.app.uxcam.spector_analytics.data.datasources.remote.AnalyticsApi
import com.app.uxcam.spector_analytics.worker.AnalyticsWorker
import com.app.uxcam.spector_analytics.domain.repository.SpectorRepository
import com.app.uxcam.spector_analytics.data.repository.SpectorRepositoryImpl
import com.app.uxcam.spector_analytics.data.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.data.datasources.local.SpectorDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val spectorModule = module {
    single {
        DeviceContext(
            sdk = Build.VERSION.SDK_INT,
            brand = Build.BRAND,
            model = Build.MODEL,
            manufacturer = Build.MANUFACTURER
        )
    }
    single {
        Retrofit.Builder()
            .baseUrl(Configuration.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnalyticsApi::class.java)
    }
    single<SpectorRepository> {
        SpectorRepositoryImpl(get(), get())
    }
    single { WorkManager.getInstance(androidContext()) }
    workerOf(::AnalyticsWorker)
    single {
        Room.databaseBuilder(
            androidContext(),
            SpectorDatabase::class.java, "spector_database"
        ).build()
    }
}