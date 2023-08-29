package com.amoon.weatherapp.data.network.models

// DataWeatherItem data class representing individual weather data items
data class DataWeatherItem(
    val clouds: Int?,     // Cloudiness percentage
    val deg: Int?,      // Wind direction in degrees
    val dt: Int?,         // Unix timestamp of the forecast
    val feels_like: DataFeelsLike?,      // Feels-like temperature values
    val gust: Double?,       // Wind gust speed
    val humidity: Int?,       // Relative humidity percentage
    val pop: Double?,        // Probability of precipitation
    val pressure: Int?,     // Atmospheric pressure in hPa
    val rain: Double?,        // Rain volume in mm
    val speed: Double?,     // Wind speed in m/s
    val sunrise: Int?,      // Unix timestamp of sunrise
    val sunset: Int?,       // Unix timestamp of sunset
    val temp: DataTemp?,       // Temperature values
    val weather: List<DataWeatherObject>? // List of weather conditions
)