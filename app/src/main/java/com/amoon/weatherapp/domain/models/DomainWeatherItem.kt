package com.amoon.weatherapp.domain.models

data class DomainWeatherItem(
    val clouds: Int,
    val deg: Int,
    val dt: Int,
    val feels_like: DomainFeelsLike,
    val gust: Double,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: DomainTemp,
    val weather: List<DomainWeatherObject>
)