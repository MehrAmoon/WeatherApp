package com.amoon.weatherapp.presentation.di

import com.amoon.weatherapp.BuildConfig
import com.amoon.weatherapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Module to provide the ApiService using Hilt
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Provides a singleton instance of ApiService
    @Singleton
    @Provides
    fun providesWeatherApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}