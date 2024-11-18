package com.app.uxcam.spector_analytics.data.datasources.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionData(
    @PrimaryKey
    @ColumnInfo(name = "session_number") var sessionNumber : Int,
    @ColumnInfo(name = "timestamp") var timeStamp: Long
)
