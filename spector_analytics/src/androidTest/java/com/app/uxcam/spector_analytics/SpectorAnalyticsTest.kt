package com.app.uxcam.spector_analytics

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.uxcam.spector_analytics.datasources.local.SpectorDatabase
import com.app.uxcam.spector_analytics.koin.spectorModule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceTimeBy
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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class SpectorAnalyticsTest : KoinTest {

    private val repository: SpectorRepository by inject()
    private val database: SpectorDatabase by inject()
    private lateinit var module: Module

    @Before
    fun setup() {
        module = module {
            single {
                Room.inMemoryDatabaseBuilder(
                    ApplicationProvider.getApplicationContext(),
                    SpectorDatabase::class.java
                ).build()
            }
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
        }
        loadKoinModules(module = module)
    }

    @Test
    fun starting_session_must_have_one_session_in_db() = runTest {
        repository.queueStartSession()
        val sessionData = database.sessionDao().getAll()
        assertThat(sessionData.size, `is`(1))

    }

    @Test
    fun starting_session_must_have_start_event_in_db() = runTest {
        repository.queueStartSession()
        val eventData = database.spectorDao().getAll()
        assertThat(eventData.size, `is`(1))
        assertThat(eventData[0].name, `is`("start_session"))
    }

    @Test
    fun new_event_within_session_timeout_must_be_in_same_session() = runTest {
        repository.queueStartSession()
        repository.queueTrack("click_event", mapOf())
        val sessionData = database.sessionDao().getAll()
        val eventData = database.spectorDao().getAll()
        assertThat(eventData.size, `is`(2))
        assertThat(eventData[1].name, `is`("track_click_event"))
        assertThat(eventData[1].sessionId, `is`(sessionData[0].sessionNumber))
        assertThat(eventData[0].sessionId, `is`(sessionData[0].sessionNumber))
    }

    @Test
    fun new_event_after_session_timeout_must_be_in_new_session() = runBlocking {
        repository.queueStartSession()
        delay(11000L)
        repository.queueTrack("click_event", mapOf())
        val sessionData = database.sessionDao().getAll()
        val eventData = database.spectorDao().getAll()
        assertThat(sessionData.size, `is`(2))
        assertThat(eventData[1].name, `is`("track_click_event"))
        assertThat(eventData[1].sessionId, `is`(sessionData[1].sessionNumber))
        assertThat(eventData[0].sessionId, `is`(sessionData[0].sessionNumber))
    }

    @After
    fun closeDatabase() {
        unloadKoinModules(module)
    }

}