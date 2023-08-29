package com.amoon.weatherapp.data.network.models

// DataWeatherObject data class representing weather condition details
data class DataWeatherObject(
    val description: String?, // Description of the weather condition
    val icon: String?,    // Icon code representing the weather condition
    val id: Int?,     // ID code for the weather condition
    val main: String?    // Main category of the weather condition
)
