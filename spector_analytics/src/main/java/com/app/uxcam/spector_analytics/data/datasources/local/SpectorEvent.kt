package com.app.uxcam.spector_analytics.data.datasources.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = SessionData::class,
            parentColumns = arrayOf("session_number"),
            childColumns = arrayOf("session_id"),
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class SpectorEvent(
    @PrimaryKey(autoGenerate = true) val uId: Long? = null,
    @ColumnInfo(name = "session_id") val sessionId: Int,
    @ColumnInfo(name = "timestamp") val timeStamp: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "event_params") val data: Map<String, String>
) {
    companion object {
        fun start(sessionId: Int, timeStamp: Long) =
            SpectorEvent(
                sessionId = sessionId,
                timeStamp = timeStamp,
                name = "start_session",
                data = mapOf()
            )

        fun end(sessionId: Int, timeStamp: Long) =
            SpectorEvent(
                sessionId = sessionId,
                timeStamp = timeStamp,
                name = "end_session",
                data = mapOf()
            )
    }
}