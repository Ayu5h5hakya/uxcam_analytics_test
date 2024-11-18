package com.app.uxcam.spector_analytics.data.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SpectorDao {
    @Query("SELECT * FROM spectorevent")
    suspend fun getAll() : List<SpectorEvent>

    @Insert
    suspend fun queue(event: SpectorEvent)
}