package com.amoon.weatherapp.data.network

import com.amoon.weatherapp.BuildConfig
import com.amoon.weatherapp.data.network.models.DataWeather
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

// ApiService interface for making network requests
@Singleton
interface ApiService {

    // Function to get weather data from the API
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,   // City name query
        @Query("units") units: String = "metric",   // Units for temperature
        @Query("appid") key: String = BuildConfig.API_ID,  // API key
    ) : DataWeather  // Returns the response as a DataWeather object

}