package com.app.uxcam.spector_analytics.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SpectorEvent::class], version = 1)
abstract class SpectorDatabase : RoomDatabase(){
    abstract fun spectorDao() : SpectorDao
}