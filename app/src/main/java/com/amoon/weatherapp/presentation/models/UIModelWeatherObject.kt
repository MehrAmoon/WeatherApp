package com.amoon.weatherapp.presentation.models

data class UIModelWeatherObject(
    val description: String,
    val icon: String,
    val id: Int?,
    val main: String?
)