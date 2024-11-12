package com.app.uxcam.spector_analytics.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpectorEvent(
    @PrimaryKey val uId: Int,
    @ColumnInfo(name = "timestamp") val timeStamp : String
)