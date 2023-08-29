package com.amoon.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amoon.weatherapp.data.database.entities.FavoriteEntity

// Represents the Room database for weather-related data
@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {
    // Abstract function to retrieve the WeatherDao interface for database access
    abstract fun weatherDao(): WeatherDao
}