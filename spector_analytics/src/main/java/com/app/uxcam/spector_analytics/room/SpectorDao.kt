package com.app.uxcam.spector_analytics.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface SpectorDao {
    @Query("SELECT * FROM spectorevent")
    fun getAll() : List<SpectorEvent>
}