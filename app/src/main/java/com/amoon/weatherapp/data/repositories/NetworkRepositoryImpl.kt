package com.amoon.weatherapp.data.repositories

import com.amoon.weatherapp.data.mappers.DataMapper
import com.amoon.weatherapp.data.network.ApiService
import com.amoon.weatherapp.domain.models.DomainWeather
import com.amoon.weatherapp.domain.repositories.NetworkRepository
import javax.inject.Inject

// NetworkRepositoryImpl class implementing the NetworkRepository interface
class NetworkRepositoryImpl @Inject constructor(
    private val api: ApiService, // Retrofit API service for making network requests
    private val dataMapper: DataMapper // Mapper for converting data models to domain models
) : NetworkRepository {

    // Get weather data for a specific city from the network and map it to domain model
    override suspend fun getWeather(
        city: String
    ): DomainWeather {
        // Make a network request to get weather data using the provided API service
        val result = api.getWeather(query = city)

        // Map the retrieved data model to a domain model using the data mapper
        return dataMapper.mapDataToDomain(result)
    }
}