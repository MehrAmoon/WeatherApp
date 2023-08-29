package com.amoon.weatherapp.domain.mappers

import com.amoon.weatherapp.domain.models.DomainWeather
import com.amoon.weatherapp.presentation.models.UIModelCords
import com.amoon.weatherapp.presentation.models.UIModelFeelsLike
import com.amoon.weatherapp.presentation.models.UIModelTemp
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherObject
import com.amoon.weatherapp.presentation.models.UIModelCity
import javax.inject.Inject


// Class responsible for mapping domain objects to UI model objects
class DomainMapper @Inject constructor() {

    // Maps a DomainWeather object to a UIModelWeather object
    fun mapDomainToUIModel(dataModel: DomainWeather): UIModelWeather {

        val list = dataModel.list.let { listItem ->
            listItem.map {
                UIModelWeatherItem(
                    it.dt ?: 0,
                    it.humidity ?: 0,
                    it.pressure ?: 0,
                    it.speed ?: 0.0,
                    it.sunrise ?: 0,
                    it.sunset ?: 0,
                    UIModelTemp(
                        it.temp?.day ?: 0.0,
                        it.temp?.max ?: 0.0,
                        it.temp?.min ?: 0.0,
                    ),
                    it.weather?.let { weatherItem ->
                        weatherItem.map { weather ->
                            UIModelWeatherObject(
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


        // Mapping UIModelCity
        val city = UIModelCity(
            country = dataModel.city.country ?: "",
            name = dataModel.city.name ?: "",
        )

        // Returning the final UIModelWeather object
        return UIModelWeather(
            city = city,
            list = list ?: listOf(),
        )
    }
}
