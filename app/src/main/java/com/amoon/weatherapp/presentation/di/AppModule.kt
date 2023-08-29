package com.amoon.weatherapp.presentation.di

import com.amoon.weatherapp.data.database.WeatherDao
import com.amoon.weatherapp.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Module to provide dependencies using Hilt
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    // Provides a WeatherDao instance as a singleton dependency
    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

}