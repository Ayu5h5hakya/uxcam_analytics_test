package com.app.uxcam.spector_analytics.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [SpectorEvent::class, SessionData::class], version = 1)
@TypeConverters(EventParamConverter::class)
abstract class SpectorDatabase : RoomDatabase(){
    abstract fun spectorDao() : SpectorDao
    abstract fun sessionDao() : SessionDao
}