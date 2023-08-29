package com.amoon.weatherapp.data.network.models

import com.google.gson.annotations.SerializedName

// DataCity data class representing city information from the API response
data class DataCity(
    @SerializedName("coord") val cords: DataCords?,  // Coordinates of the city
    val country: String?, // Country name
    val id: Int?,  // City ID
    val name: String?,  // City name
    val population: Int?,  // City population
    val timezone: Int?  // Timezone offset
)