package com.amoon.weatherapp.domain.models


data class DomainWeather(
    val city: DomainCity,
    val cnt: Int,
    val cod: String,
    val list: List<DomainWeatherItem>,
    val message: Double
)