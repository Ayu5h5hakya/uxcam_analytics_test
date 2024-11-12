package com.app.uxcam.spector_analytics.koin

import androidx.room.Room
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.AnalyticsApi
import com.app.uxcam.spector_analytics.AnalyticsWorker
import com.app.uxcam.spector_analytics.SpectorRepository
import com.app.uxcam.spector_analytics.SpectorRepositoryImpl
import com.app.uxcam.spector_analytics.room.SpectorDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.workerOf
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
        SpectorRepositoryImpl(get(), get())
    }
    single { WorkManager.getInstance(androidContext()) }
    //workerOf(::AnalyticsWorker)
    single { Room.databaseBuilder(
        androidContext(),
        SpectorDatabase::class.java, "spector_database"
    ).build()}
}