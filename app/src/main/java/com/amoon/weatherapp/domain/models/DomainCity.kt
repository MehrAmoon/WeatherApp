package com.amoon.weatherapp.domain.models


// Represents a city in the domain layer
data class DomainCity(
    val cords: DomainCords, // Coordinates of the city
    val country: String,    // Country of the city
    val id: Int,            // ID of the city
    val name: String,       // Name of the city
    val population: Int,    // Population of the city
    val timezone: Int       // Timezone of the city
)