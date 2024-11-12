package com.app.uxcam.spector_analytics.koin

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import com.app.uxcam.spector_analytics.AnalyticsApi
import com.app.uxcam.spector_analytics.SpectorRepository
import com.app.uxcam.spector_analytics.SpectorRepositoryImpl
import com.app.uxcam.spector_analytics.room.SpectorDatabase
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
        SpectorRepositoryImpl(get(), get(), get())
    }
    single { (context: Context) -> WorkManager.getInstance(context) }
    single { (context: Context) -> Room.databaseBuilder(
        context,
        SpectorDatabase::class.java, "database-name"
    ).build() }
}