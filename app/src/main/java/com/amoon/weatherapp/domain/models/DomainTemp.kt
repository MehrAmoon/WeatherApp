package com.amoon.weatherapp.domain.models

data class DomainTemp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
)