package com.amoon.weatherapp.data.network.models

// DataWeather data class representing weather data from the API response
data class DataWeather(
    val city: DataCity?,    // Information about the city
    val cnt: Int?,     // Count of forecasted weather items
    val cod: String?,     // Status code of the API response
    val list: List<DataWeatherItem>?, // List of weather items
    val message: Double?     // Message from the API response
)