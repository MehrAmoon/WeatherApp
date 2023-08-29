package com.amoon.weatherapp

import com.amoon.weatherapp.data.database.entities.FavoriteEntity
import com.amoon.weatherapp.data.network.models.DataCity
import com.amoon.weatherapp.data.network.models.DataCords
import com.amoon.weatherapp.data.network.models.DataFeelsLike
import com.amoon.weatherapp.data.network.models.DataTemp
import com.amoon.weatherapp.data.network.models.DataWeather
import com.amoon.weatherapp.data.network.models.DataWeatherItem
import com.amoon.weatherapp.data.network.models.DataWeatherObject
import com.amoon.weatherapp.domain.models.DomainCity
import com.amoon.weatherapp.domain.models.DomainCords
import com.amoon.weatherapp.domain.models.DomainFeelsLike
import com.amoon.weatherapp.domain.models.DomainTemp
import com.amoon.weatherapp.domain.models.DomainWeather
import com.amoon.weatherapp.domain.models.DomainWeatherItem
import com.amoon.weatherapp.domain.models.DomainWeatherObject
import com.amoon.weatherapp.presentation.models.UIModelCity
import com.amoon.weatherapp.presentation.models.UIModelCords
import com.amoon.weatherapp.presentation.models.UIModelFeelsLike
import com.amoon.weatherapp.presentation.models.UIModelTemp
import com.amoon.weatherapp.presentation.models.UIModelWeather
import com.amoon.weatherapp.presentation.models.UIModelWeatherItem
import com.amoon.weatherapp.presentation.models.UIModelWeatherObject

object MockUtils {

    // data response
    val dataCords = DataCords(52.2,52.2)
    val dataCity =  DataCity (dataCords,"DE",10,"Berlin",100,7200)
    val dataFeelsLike= DataFeelsLike(0.8, 0.8, 0.8, 0.8,)
    val dataTemp= DataTemp(0.9, 0.9, 0.9, 0.9, 0.9, 0.9,)
    val dataWeatherObject = DataWeatherObject(   "description", "icon", 1, "main")
    val dataWeatherItem = DataWeatherItem(90, 1, 1, dataFeelsLike, 0.9, 1, 0.9, 1, 0.9, 0.9, 1, 1, dataTemp, listOf(dataWeatherObject))
    val apiResponse = DataWeather(dataCity, 7, "200", listOf(dataWeatherItem) ,0.0511463)

    //domain response
    val domainCords = DomainCords(52.2,52.2)
    val domainCity =  DomainCity (domainCords,"DE",10,"Berlin",100,7200)
    val domainFeelsLike= DomainFeelsLike(0.8, 0.8, 0.8, 0.8,)
    val domainTemp= DomainTemp(0.9, 0.9, 0.9, 0.9, 0.9, 0.9,)
    val domainWeatherObject = DomainWeatherObject(   "description", "icon", 1, "main")
    val domainWeatherItem = DomainWeatherItem(90, 1, 1, domainFeelsLike, 0.9, 1, 0.9, 1, 0.9, 0.9, 1, 1, domainTemp, listOf(domainWeatherObject))
    val domainWeather = DomainWeather(domainCity, 7, "200", listOf(domainWeatherItem) ,0.0511463)


    //UIModelResponse
    val uIModelCords = UIModelCords(52.2, 52.2)
    val uIModelCity = UIModelCity(uIModelCords, "DE", 10, "Berlin", 100, 7200)
    val uIModelFeelsLike = UIModelFeelsLike(0.8, 0.8, 0.8, 0.8)
    val uIModelTemp = UIModelTemp(0.9, 0.9, 0.9, 0.9, 0.9, 0.9)
    val uIModelWeatherObject = UIModelWeatherObject("sky is clear", "icon", 1, "main")
    val uIModelWeatherItem = UIModelWeatherItem(
        90,
        1,
        1693047600,
        uIModelFeelsLike,
        0.9,
        1,
        0.9,
        1,
        0.9,
        0.9,
        1692677414,
        1692726854,
        uIModelTemp,
        listOf(uIModelWeatherObject)
    )
    val uIModelWeather =
        UIModelWeather(uIModelCity, 7, "200", listOf(uIModelWeatherItem), 0.0511463)


    val mockFavoriteEntity = FavoriteEntity("Tehran","IR")

}