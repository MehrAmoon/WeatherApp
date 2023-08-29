package com.amoon.weatherapp.domain.models

data class DomainWeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)