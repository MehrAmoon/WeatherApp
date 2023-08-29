package com.amoon.weatherapp.presentation.di

import android.content.Context
import androidx.room.Room
import com.amoon.weatherapp.BuildConfig
import com.amoon.weatherapp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module to provide the WeatherDatabase using Hilt
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    // Provides a singleton instance of WeatherDatabase
    @Singleton
    @Provides
    fun provideWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            BuildConfig.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}