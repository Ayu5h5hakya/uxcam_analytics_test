package com.app.uxcam.spector_analytics.datasources.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SessionDao {
    @Query("SELECT * FROM sessiondata")
    suspend fun getAll() : List<SessionData>

    @Insert
    suspend fun add(data : SessionData)

    @Query("UPDATE sessiondata SET timestamp = :timeStamp WHERE session_number = :sessionNumber")
    suspend fun update(sessionNumber: Int, timeStamp : Long)

    @Query("DELETE FROM sessiondata WHERE session_number = :sessionNumber")
    suspend fun delete(sessionNumber: Int)

    @Query("DELETE FROM sessiondata")
    suspend fun deleteAll()

    @Query("SELECT * FROM sessiondata ORDER BY session_number DESC LIMIT 1")
    suspend fun getLatestSessionData() : SessionData
}