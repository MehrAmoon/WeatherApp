package com.amoon.weatherapp.presentation.models

data class UIModelWeather(
    val city: UIModelCity,
    val list: List<UIModelWeatherItem>,
)