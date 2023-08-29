package com.amoon.weatherapp.presentation.models

data class UIModelWeatherItem(
    val dt: Int,
    val humidity: Int,
    val pressure: Int,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: UIModelTemp,
    val weather: List<UIModelWeatherObject>
)