package com.amoon.weatherapp.domain.repositories

import com.amoon.weatherapp.domain.models.DomainWeather

// Represents a repository for fetching weather data from a network source
interface NetworkRepository {

    // Retrieves weather data for a specified city from the network
    suspend fun getWeather(city: String): DomainWeather

}