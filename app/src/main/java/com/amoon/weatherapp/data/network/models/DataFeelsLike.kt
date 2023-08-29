package com.amoon.weatherapp.data.network.models

// DataFeelsLike data class representing feels-like temperature values from the API response
data class DataFeelsLike(
    val day: Double?,   // Feel-like temperature during the day
    val eve: Double?,   // Feel-like temperature during the evening
    val morn: Double?,  // Feel-like temperature during the morning
    val night: Double?  // Feel-like temperature during the night
)