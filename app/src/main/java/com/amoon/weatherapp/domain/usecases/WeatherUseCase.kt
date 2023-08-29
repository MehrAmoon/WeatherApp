package com.amoon.weatherapp.domain.usecases

import com.amoon.weatherapp.domain.mappers.DomainMapper
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.domain.repositories.NetworkRepository
import javax.inject.Inject

// Represents a use case for retrieving weather data
open class WeatherUseCase @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val domainMapper: DomainMapper
) {

    // Retrieves weather data for a given city and maps it to a UIModelWeather
    open suspend fun weather(city: String): UIModelWeather {
        val domainResult = networkRepository.getWeather(city)
        return domainMapper.mapDomainToUIModel(domainResult)
    }
}
