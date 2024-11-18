package com.app.uxcam.spector_analytics

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.testing.SynchronousExecutor
import androidx.work.testing.WorkManagerTestInitHelper
import com.app.uxcam.spector_analytics.datasources.local.DeviceContext
import com.app.uxcam.spector_analytics.datasources.local.SpectorDatabase
import com.app.uxcam.spector_analytics.datasources.remote.SpectorData
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AnalyticsWorkerTest : KoinTest {

    private val repository: SpectorRepository by inject()
    private val database: SpectorDatabase by inject()
    private lateinit var context: Context
    private lateinit var module: Module

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        module = module {
            single {
                Room.inMemoryDatabaseBuilder(
                    ApplicationProvider.getApplicationContext(),
                    SpectorDatabase::class.java
                ).build()
            }
            single {
                mockk<AnalyticsApi>()
            }
            single<SpectorRepository> {
                SpectorRepositoryImpl(get(), get())
            }
            single {
                DeviceContext(
                    manufacturer = "test_manufacturer",
                    sdk = 35,
                    model = "test_model",
                    brand = "test_brand"
                )
            }
        }
        loadKoinModules(module = module)
    }

    @Test
    fun analytics_should_work_every_15_minutes() {
        WorkManagerTestInitHelper.initializeTestWorkManager(context)
        val workManager = WorkManager.getInstance(context)
        val request = PeriodicWorkRequestBuilder<AnalyticsWorker>(15, TimeUnit.MINUTES).build()
        workManager.enqueue(request).result.get()
        WorkManagerTestInitHelper.getTestDriver(context)?.setInitialDelayMet(request.id)
        WorkManagerTestInitHelper.getTestDriver(context)?.setPeriodDelayMet(request.id)
        val workInfo = workManager.getWorkInfoById(request.id).get()
        assertThat(workInfo!!.state, `is`(WorkInfo.State.RUNNING))
    }

    @Test
    fun db_should_be_flushed_after_analytics_sync_is_complete() = runTest {
        repository.queueStartSession()
        repository.queueTrack("click_event", mapOf())
        assertThat(database.sessionDao().getAll().size, `is`(1))
        assertThat(database.spectorDao().getAll().size, `is`(2))

        val deviceContext: DeviceContext by inject()
        val api: AnalyticsApi by inject()
        coEvery {
            api.sendAnalyticsData(
                SpectorData(
                    deviceContext,
                    repository.getEventQueue()
                )
            )
        } returns Unit

        WorkManagerTestInitHelper.initializeTestWorkManager(
            context, Configuration.Builder()
                .setExecutor(SynchronousExecutor())
                .build()
        )
        val request = OneTimeWorkRequestBuilder<AnalyticsWorker>().build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(request).result.get()

        val testDriver = WorkManagerTestInitHelper.getTestDriver(context)
        testDriver!!.setInitialDelayMet(request.id)

        val workInfo = workManager.getWorkInfoById(request.id).get()
        assertThat(workInfo!!.state, `is`(WorkInfo.State.SUCCEEDED))

        assertThat(database.sessionDao().getAll().size, `is`(0))
        assertThat(database.spectorDao().getAll().size, `is`(0))
    }

    @After
    fun closeDatabase() {
        unloadKoinModules(module)
    }
}