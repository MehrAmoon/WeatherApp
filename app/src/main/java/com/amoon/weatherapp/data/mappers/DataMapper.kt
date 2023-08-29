package com.amoon.weatherapp.data.mappers

import com.amoon.weatherapp.data.network.models.DataWeather
import com.amoon.weatherapp.domain.models.DomainCity
import com.amoon.weatherapp.domain.models.DomainCords
import com.amoon.weatherapp.domain.models.DomainFeelsLike
import com.amoon.weatherapp.domain.models.DomainTemp
import com.amoon.weatherapp.domain.models.DomainWeather
import com.amoon.weatherapp.domain.models.DomainWeatherItem
import com.amoon.weatherapp.domain.models.DomainWeatherObject
import javax.inject.Inject

// DataMapper class responsible for mapping data models to domain models
class DataMapper @Inject constructor() {

    // Function to map data model to domain model
    fun mapDataToDomain(dataModel: DataWeather): DomainWeather {

        // Map list of data model to list of domain model
        val list = dataModel.list.let { listItem ->
            listItem?.map {
                DomainWeatherItem(
                    it.clouds ?: 0,
                    it.deg ?: 0,
                    it.dt ?: 0,
                    DomainFeelsLike(
                        it.feels_like?.day ?: 0.0,
                        it.feels_like?.eve ?: 0.0,
                        it.feels_like?.morn ?: 0.0,
                        it.feels_like?.night ?: 0.0,
                    ),
                    it.gust ?: 0.0,
                    it.humidity ?: 0,
                    it.pop ?: 0.0,
                    it.pressure ?: 0,
                    it.rain ?: 0.0,
                    it.speed ?: 0.0,
                    it.sunrise ?: 0,
                    it.sunset ?: 0,
                    DomainTemp(
                        it.temp?.day ?: 0.0,
                        it.temp?.eve ?: 0.0,
                        it.temp?.max ?: 0.0,
                        it.temp?.min ?: 0.0,
                        it.temp?.morn ?: 0.0,
                        it.temp?.night ?: 0.0,
                    ),
                    it.weather?.let { weatherItem ->
                        weatherItem.map { weather ->
                            DomainWeatherObject(
                                weather.description ?: "",
                                weather.icon ?: "",
                                weather.id ?: 0,
                                weather.main ?: "",
                            )
                        }
                    } ?: listOf()
                )
            }
        }

        // Map cords data to domain model
        val cords = DomainCords(
            lat = dataModel.city?.cords?.lat ?: 0.0,
            lon = dataModel.city?.cords?.lat ?: 0.0,
        )

        // Map city data to domain model
        val city = DomainCity(
            cords = cords,
            country = dataModel.city?.country ?: "",
            id = dataModel.city?.id ?: 0,
            name = dataModel.city?.name ?: "",
            population = dataModel.city?.population ?: 0,
            timezone = dataModel.city?.timezone ?: 0,
        )

        // Map data model to domain model
        return DomainWeather(
            city = city, // Handle null
            cnt = dataModel.cnt ?: 0,
            cod = dataModel.cod ?: "",
            list = list ?: listOf(),
            message = dataModel.message ?: 0.0
        )
    }
}