package com.amoon.weatherapp.data.network.models

// DataTemp data class representing temperature values from the API response
data class DataTemp(
    val day: Double?,   // Temperature during the day
    val eve: Double?,   // Temperature during the evening
    val max: Double?,   // Maximum temperature of the day
    val min: Double?,   // Minimum temperature of the day
    val morn: Double?,  // Temperature during the morning
    val night: Double?  // Temperature during the night
)